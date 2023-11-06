package be.motormouth.division;

import be.motormouth.division.services.DivisionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;

@Path("/divisions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DivisionController {
    @Inject
    private DivisionService divisionService;

    @GET
    @ResponseStatus(200)
    public Collection<DivisionDTO> viewAllDivisions(@RestHeader String authorization
            , @QueryParam("divisionid") @DefaultValue("ALL") String divisionId) {
        //User connectedUser = securityService.validateAuthorization(authorization, VIEW_ALL_DIVISIONS);
        return divisionMapper.toDTO(divisionService.viewAllDivisions(divisionId));
    }
    @POST
    @ResponseStatus(201)
    public DivisionDTO createDivision(@RestHeader String authorization
            , DivisionDTO divisionDTO) {
        //User connectedUser = securityService.validateAuthorization(authorization, CREATE_DIVISION);
        return divisionMapper.toDTO(divisionService.createDivision(divisionDTO));
    }
}
