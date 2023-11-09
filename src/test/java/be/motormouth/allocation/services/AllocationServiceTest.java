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
    void startAllocation() {
        // given
        CreateAllocationDto createAllocationDto = new CreateAllocationDto(
                PARKING_LOT_1.getId(),
                USER_SILVER.getMember().getLicencePlate().getLicensePlateNumber());

        Mockito.when(parkingLotService.getParkingLot(createAllocationDto.parkingLotId())).thenReturn(PARKING_LOT_1);
        Mockito.when(allocationPanacheRepository.createAllocation(ALLOCATION_1)).thenReturn(ALLOCATION_1);

        // when
        Allocation actualAllocation = allocationService.startAllocation(createAllocationDto, USER_SILVER);

        // then
        Assertions
                .assertThat(actualAllocation)
                .isEqualTo(ALLOCATION_1);
    }
}