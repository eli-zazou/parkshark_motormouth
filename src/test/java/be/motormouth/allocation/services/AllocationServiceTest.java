package be.motormouth.allocation.services;

import be.motormouth.allocation.AllocationPanacheRepository;
import be.motormouth.allocation.dto.CreateAllocationDto;
import be.motormouth.allocation.entities.Allocation;
import be.motormouth.member.services.MemberService;
import be.motormouth.parkinglot.services.ParkingLotService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static be.motormouth.testConstants.AllocationTestConstants.ALLOCATION_1;
import static be.motormouth.testConstants.ParkingLotTestConstants.PARKING_LOT_1;
import static be.motormouth.testConstants.UserTestConstants.USER_SILVER;
import static org.mockito.ArgumentMatchers.refEq;

class AllocationServiceTest {
    private AllocationPanacheRepository allocationPanacheRepository;
    private ParkingLotService parkingLotService;
    private MemberService memberService;
    private AllocationService allocationService;

    @BeforeEach
    void setUp() {
        allocationPanacheRepository = Mockito.mock(AllocationPanacheRepository.class);
        parkingLotService = Mockito.mock(ParkingLotService.class);
        memberService = Mockito.mock(MemberService.class);
        allocationService = new AllocationService(allocationPanacheRepository, parkingLotService, memberService);
    }

    @Test
    void startAllocation_givenCreateAllocationDto_thenAllocationIsSavedToRepoAndReturned() {
        // given
        CreateAllocationDto createAllocationDto = new CreateAllocationDto(
                1L,
                USER_SILVER.getMember().getLicencePlate().getLicensePlateNumber());
        Mockito.when(parkingLotService.getParkingLot(1L)).thenReturn(PARKING_LOT_1);
        Mockito.when(allocationPanacheRepository.createAllocation(refEq(ALLOCATION_1, "startTime"))).thenReturn(ALLOCATION_1);

        // when
        Allocation actualAllocation = allocationService.startAllocation(createAllocationDto, USER_SILVER);

        // then
        Mockito.verify(allocationPanacheRepository).createAllocation(actualAllocation);
        Assertions
                .assertThat(actualAllocation)
                .usingRecursiveComparison()
                .ignoringFields("startTime")
                .isEqualTo(ALLOCATION_1);
    }
}