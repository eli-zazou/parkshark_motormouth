package be.motormouth.parkinglot;

import be.motormouth.parkinglot.dtos.ParkingLotDto;
import be.motormouth.parkinglot.services.ParkingLotMapper;
import be.motormouth.parkinglot.services.ParkingLotService;
import be.motormouth.security.SecurityService;
import be.motormouth.security.users.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestHeader;

import java.util.List;

import static be.motormouth.security.Feature.*;

@Path("/parkinglots")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParkingLotController {
    @Inject
    SecurityService securityService;
    @Inject
    ParkingLotService parkingLotService;

    @GET
    public List<ParkingLotDto> getAllParkingLots(@RestHeader String authorization) {
        User connectedUser = securityService.validateAuthorization(authorization, VIEW_ALL_PARKING_LOTS);
        return ParkingLotMapper.toDto(parkingLotService.getAllParkingLots());
    }

    @GET
    @Path("{id}")
    public ParkingLotDto getParkingLot(@RestHeader String authorization, @PathParam("id") String id){
        User connectedUser = securityService.validateAuthorization(authorization, VIEW_A_PARKING_LOT);
        return ParkingLotMapper.toDto(parkingLotService.getParkingLot(Long.parseLong(id)));
    }

}
