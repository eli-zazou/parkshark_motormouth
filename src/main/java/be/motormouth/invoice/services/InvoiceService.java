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
        invoicePanacheRepository.createInvoice(invoice);
        return invoice;
    }

    private static LocalDateTime calculateInvoiceDate() {
        YearMonth calculation = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth().minus(1));
        return calculation.atEndOfMonth().atStartOfDay();
    }

    private List<InvoiceItem> getAllInvoiceItems(Invoice invoice) {
        return getAllOpenAllocations(invoice.getInvoiceDate(), invoice.getMember())
                .stream()
                .map(allocation -> new InvoiceItem(invoice, allocation, calculatePrice(allocation)))
                .toList();
    }

    private double calculatePrice(Allocation allocation) {
        double duration = (double) allocation.calculateDuration().toMinutes() / 60;
        int roundedUpDuration = (int) (allocation.calculateDuration().toMinutes() / 60);
        if ((duration - roundedUpDuration) > 0 ) roundedUpDuration++;
        double pricePerHour = allocation.getParkingLot().getPricePerHourInEuro();
        double reductionPerentage = 1 - (allocation.getMember().getMembershipLevel().getReductionInPercentage() / 100);
        double monthlyCost = allocation.getMember().getMembershipLevel().getMonthlyCost();
        double fine = 0;
        if (roundedUpDuration > allocation.getMember().getMembershipLevel().getMaxAllocationTimeInHour()) {
            fine = (roundedUpDuration - allocation.getMember().getMembershipLevel().getMaxAllocationTimeInHour()) * FINE_EXCEEDING_TIME_LIMIT;
        }
//        logger.info("Duration:" + duration);
//        logger.info("Rounded Duration:" + roundedUpDuration);
//        logger.info("price:" + pricePerHour);
//        logger.info("reduction:" + reductionPerentage + " -> " + allocation.getMember().getMembershipLevel().getReductionInPercentage() + "%");
//        logger.info("Cost:" + monthlyCost);
//        logger.info("fine:" + fine);
        allocation.setAllocationStatus(AllocationStatus.INVOICED);
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

    public Collection<Invoice> getAllInvoices() {
        return invoicePanacheRepository.getAllInvoices();
    }

    public void markAsClosed(String invoiceId) {
        Invoice invoice = invoicePanacheRepository.findInvoiceById(Long.parseLong(invoiceId)).orElseThrow(NotFoundException::new);
        if (invoice.getInvoiceStatus() != InvoiceStatus.OPEN)
            throw new AlreadyClosedInvoiceException(
                    "Invoice with id \"" + invoice.getId() + "\" belonging to " + invoice.getMember().getFirstName() + " " + invoice.getMember().getLastName()
            + " is already closed.");
        invoice.setInvoiceStatus(InvoiceStatus.CLOSED);
    }
}
