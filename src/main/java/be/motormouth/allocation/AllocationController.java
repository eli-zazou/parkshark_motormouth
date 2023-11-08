package be.motormouth.allocation;

import be.motormouth.allocation.dto.AllocationDto;
import be.motormouth.allocation.dto.CreateAllocationDto;
import be.motormouth.allocation.services.AllocationService;
import be.motormouth.allocation.services.mapper.AllocationMapper;
import be.motormouth.security.SecurityService;
import be.motormouth.security.users.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;

import java.util.Collection;

import static be.motormouth.security.Feature.*;

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

    @GET
    @ResponseStatus(200)
    public Collection<AllocationDto> viewAllAllocations(@RestHeader String authorization, CreateAllocationDto createAllocationDto) {
        User connectedUser = securityService.validateAuthorization(authorization, GET_ALL_PARKING_ALLOCATIONS);
        return AllocationMapper.mapToDto(
                allocationService.viewAllAllocations());
    }
    @POST
    @ResponseStatus(201)
    public AllocationDto startAllocation(@RestHeader String authorization, CreateAllocationDto createAllocationDto) {
        User connectedUser = securityService.validateAuthorization(authorization, ALLOCATE_PARKING_SPOT);
        return AllocationMapper.mapToDto(
                allocationService.startAllocation(createAllocationDto, connectedUser));
    }
    @PATCH
    @Path("/{id}")
    @ResponseStatus(201)
    public AllocationDto stopAllocation(@RestHeader String authorization, String id) {
        User connectedUser = securityService.validateAuthorization(authorization, END_ALLOCATION_PARKING_SPOT);
        return AllocationMapper.mapToDto(
                allocationService.stopAllocation(connectedUser, id));
    }
}