package be.motormouth.invoice;

import be.motormouth.division.DivisionPanacheRepository;
import be.motormouth.invoice.entities.Invoice;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.util.Collection;
import java.util.Optional;
@ApplicationScoped
public class InvoicePanacheRepository implements PanacheRepository<Invoice> {
    private final Logger logger = Logger.getLogger(DivisionPanacheRepository.class);
    public Collection<Invoice> getAllInvoices() {
        return listAll();
    }
    public Optional<Invoice> findInvoiceById(Long id) {
        return findByIdOptional(id);
    }
    public Invoice createInvoice(Invoice invoice) {
        persist(invoice);
        return invoice;
    }
}
