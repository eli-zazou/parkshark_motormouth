package be.motormouth.allocation;

import be.motormouth.allocation.dto.CreateAllocationDto;
import be.motormouth.allocation.services.AllocationService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;

@Path("/allocations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AllocationController {
    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService){
        this.allocationService = allocationService;
    }

    @POST
    @ResponseStatus(201)
    public Response addAllocation(CreateAllocationDto createAllocationDto) {
        throw new RuntimeException("Not Implemented");
    }
}
