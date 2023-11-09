package be.motormouth.invoice;

import be.motormouth.invoice.dto.InvoiceDTO;
import be.motormouth.invoice.services.InvoiceMapper;
import be.motormouth.invoice.services.InvoiceService;
import be.motormouth.security.Feature;
import be.motormouth.security.SecurityService;
import be.motormouth.security.users.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;

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

    @GET
    public Response getAllInvoices(@RestHeader String authorization){
        User connectedUser = securityService.validateAuthorization(authorization, Feature.GET_ALL_INVOICES);
        return Response.ok().entity(InvoiceMapper.toDTO(invoiceService.getAllInvoices())).build();
    }

    @PATCH
    @Path("/{id}")
    public Response markInvoiceAsClosed(@RestHeader String authorization, @PathParam("id") String invoiceId){
        User connectedUser = securityService.validateAuthorization(authorization, Feature.MARK_INVOICES_CLOSED);
        invoiceService.markAsClosed(invoiceId);
        return Response.ok().build();
    }

}
