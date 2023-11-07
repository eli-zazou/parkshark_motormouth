package be.motormouth.allocation;

import be.motormouth.allocation.dto.AllocationDto;
import be.motormouth.allocation.dto.CreateAllocationDto;
import be.motormouth.allocation.services.AllocationService;
import be.motormouth.allocation.services.mapper.AllocationMapper;
import be.motormouth.security.SecurityService;
import be.motormouth.security.users.User;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;

import static be.motormouth.security.Feature.ALLOCATE_PARKING_SPOT;

@Path("/allocations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AllocationController {
    private final AllocationService allocationService;
    private final SecurityService securityService;

    public AllocationController(AllocationService allocationService, SecurityService securityService) {
        this.allocationService = allocationService;
        this.securityService = securityService;
    }

    @POST
    @ResponseStatus(201)
    public AllocationDto addAllocation(@RestHeader String authorization, CreateAllocationDto createAllocationDto) {
        User connectedUser = securityService.validateAuthorization(authorization, ALLOCATE_PARKING_SPOT);
        return AllocationMapper.mapToDto(
                allocationService.addAllocation(createAllocationDto, connectedUser));
    }
}