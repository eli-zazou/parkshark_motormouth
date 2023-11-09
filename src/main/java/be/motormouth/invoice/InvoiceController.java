package be.motormouth.invoice;

import be.motormouth.division.DivisionPanacheRepository;
import be.motormouth.invoice.dto.InvoiceDTO;
import be.motormouth.invoice.entities.Invoice;
import be.motormouth.invoice.services.InvoiceMapper;
import be.motormouth.invoice.services.InvoiceService;
import be.motormouth.security.Feature;
import be.motormouth.security.SecurityService;
import be.motormouth.security.users.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;

import java.util.Collection;
import java.util.Optional;

@Path("/invoices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InvoiceController {
    @Inject
    SecurityService securityService;
    @Inject
    InvoiceService invoiceService;
    @POST
    @ResponseStatus(200)
    public InvoiceDTO createMonthlyInvoice(@RestHeader String authorization) {
        User connectedUser = securityService.validateAuthorization(authorization, Feature.GET_MONTHLY_INVOICE);
        return InvoiceMapper.toDTO(invoiceService.getMonthlyInvoice(connectedUser));
    }

}
