package be.motormouth.parkinglot;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/parkinglots")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParkingLotController {

    @GET
    public List<ParkingLotDto> getAllParkingLots(){
        return new ArrayList<>();
    }

    @Path("{id}")
    @GET
    public ParkingLotDto getParkingLot(@PathParam("id") String id){
        return null;
    }

    @POST
    public String createParkingLot(CreateParkingLotDto parkingLotDto){
        return null;
    }
}
