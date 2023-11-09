package be.motormouth.invoice.services;

import be.motormouth.allocation.entities.Allocation;
import be.motormouth.allocation.entities.AllocationStatus;
import be.motormouth.allocation.services.AllocationService;
import be.motormouth.division.services.DivisionService;
import be.motormouth.exceptions.AlreadyClosedInvoiceException;
import be.motormouth.invoice.InvoicePanacheRepository;
import be.motormouth.invoice.entities.Invoice;
import be.motormouth.invoice.entities.InvoiceItem;
import be.motormouth.invoice.entities.InvoiceStatus;
import be.motormouth.member.entities.Member;
import be.motormouth.security.users.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Collection;
import java.util.List;

@ApplicationScoped
@Transactional
public class InvoiceService {
    public static final double FINE_EXCEEDING_TIME_LIMIT = 2.5;
    private final InvoicePanacheRepository invoicePanacheRepository;
    private final AllocationService allocationService;
    private final Logger logger = Logger.getLogger(DivisionService.class);
    private String errorMessage;

    public InvoiceService(InvoicePanacheRepository invoicePanacheRepository, AllocationService allocationService) {
        this.invoicePanacheRepository = invoicePanacheRepository;
        this.allocationService = allocationService;
    }

    public Invoice getMonthlyInvoice(User authorisedUser) {
        LocalDateTime invoiceDate = calculateInvoiceDate();
        //
        if (getAllOpenAllocations(invoiceDate, authorisedUser.getMember()).isEmpty()) throw new IllegalArgumentException("Nothing to Invoice");
        Invoice invoice = new Invoice(invoiceDate, authorisedUser.getMember(), LocalDate.now(), LocalDate.now().plusDays(30), InvoiceStatus.OPEN, null);
        //
        invoice.setInvoiceItems(getAllInvoiceItems(invoice));
        return null;
    }

    private static LocalDateTime calculateInvoiceDate() {
        YearMonth calculation = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth().minus(1));
        LocalDateTime invoiceDate = calculation.atEndOfMonth().atStartOfDay();
        invoiceDate.plusSeconds(86399); // set time to 23:59:59
        return invoiceDate;
    }

    private List<InvoiceItem> getAllInvoiceItems(Invoice invoice) {
        return getAllOpenAllocations(invoice.getInvoiceDate(), invoice.getMember())
                .stream()
                .map(allocation -> new InvoiceItem(invoice, allocation, calculatePrice(allocation)))
                .toList();
    }

    private double calculatePrice(Allocation allocation) {
        double roundedUpDuration = Math.ceil(allocation.calculateDuration().toHours());
        double pricePerHour = allocation.getParkingLot().getPricePerHourInEuro();
        double reductionPerentage = 1 - (allocation.getMember().getMembershipLevel().getReductionInPercentage() / 100);
        double monthlyCost = allocation.getMember().getMembershipLevel().getMonthlyCost();
        double fine = 0;
        if (roundedUpDuration > allocation.getMember().getMembershipLevel().getMaxAllocationTimeInHour()) {
            fine = (roundedUpDuration - allocation.getMember().getMembershipLevel().getMaxAllocationTimeInHour()) * FINE_EXCEEDING_TIME_LIMIT;
        }
        return (roundedUpDuration * (pricePerHour * reductionPerentage)) + fine + monthlyCost;
    }
    private List<Allocation> getAllOpenAllocations(LocalDateTime invoiceDate, Member member) {
        return allocationService.viewAllAllocations()
                .stream()
                .filter(allocation -> allocation.getAllocationStatus().equals(AllocationStatus.NOT_YET_INVOICED))
                .filter(allocation -> allocation.getMember().equals(member))
                .filter(allocation -> !allocation.isActive())
                .filter(allocation -> allocation.getEndTime().isBefore(invoiceDate))
                .toList();
    }

    public Collection<Invoice> getAll() {
        return invoicePanacheRepository.getAllInvoices();
    }

    @Transactional
    public void markAsClosed(String invoiceId) {
        Invoice invoice = invoicePanacheRepository.findInvoiceById(Long.parseLong(invoiceId)).orElseThrow(NotFoundException::new);
        if (invoice.getInvoiceStatus() != InvoiceStatus.OPEN)
            throw new AlreadyClosedInvoiceException(
                    "Invoice with id \"" + invoice.getId() + "\" belonging to " + invoice.getMember().getFirstName() + " " + invoice.getMember().getLastName()
            + " is already closed.");
        invoice.setInvoiceStatus(InvoiceStatus.CLOSED);
    }
}
