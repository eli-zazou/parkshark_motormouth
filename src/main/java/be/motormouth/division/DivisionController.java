package be.motormouth.division;

import be.motormouth.division.dto.DivisionDTO;
import be.motormouth.division.services.DivisionMapper;
import be.motormouth.division.services.DivisionService;
import be.motormouth.parkinglot.dtos.CreateParkingLotDto;
import be.motormouth.parkinglot.dtos.ParkingLotDto;
import be.motormouth.security.SecurityService;
import be.motormouth.security.users.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;

import java.util.Collection;

import static be.motormouth.security.Feature.*;

@Path("/divisions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DivisionController {
    @Inject
    DivisionService divisionService;
    @Inject
    SecurityService securityService;

    @GET
    @ResponseStatus(200)
    public Collection<DivisionDTO> viewAllDivisions(@RestHeader String authorization
            , @QueryParam("divisionid") @DefaultValue("ALL") String divisionId) {
        User connectedUser = securityService.validateAuthorization(authorization, VIEW_ALL_DIVISIONS);
        return DivisionMapper.toDTO(divisionService.viewAllDivisions(divisionId));
    }
    @GET
    @Path("/{id}")
    @ResponseStatus(200)
    public DivisionDTO viewDivisionsById(@RestHeader String authorization, @PathParam("id") String id) {
        User connectedUser = securityService.validateAuthorization(authorization, VIEW_A_DIVISION);
        return DivisionMapper.toDTO(divisionService.viewDivisionsById(id));
    }
    @POST
    @ResponseStatus(201)
    public DivisionDTO createDivision(@RestHeader String authorization
            , DivisionDTO divisionDTO) {
        User connectedUser = securityService.validateAuthorization(authorization, CREATE_DIVISION);
        return DivisionMapper.toDTO(divisionService.createDivision(null, divisionDTO));
    }
    @POST
    @Path("/{id}")
    @ResponseStatus(201)
    public DivisionDTO createSubDivision(@RestHeader String authorization, @PathParam("id") String id
            , DivisionDTO divisionDTO) {
        User connectedUser = securityService.validateAuthorization(authorization, CREATE_SUB_DIVISION);
        return DivisionMapper.toDTO(divisionService.createDivision(id, divisionDTO));
    }

    @POST
    @Path("/{id}/parkinglot")
    public ParkingLotDto createParkingLot(CreateParkingLotDto parkingLotDto, @PathParam("id")Long divisionId){
//        return ParkingLotMapper.toDto();
        return null;
    }
}
