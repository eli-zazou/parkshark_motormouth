package be.motormouth.allocation;

import be.motormouth.allocation.entities.Allocation;
import be.motormouth.member.MemberPanacheRepository;
import be.motormouth.member.entities.Member;
import be.motormouth.parkinglot.ParkingLotPanacheRepository;
import be.motormouth.parkinglot.entities.ParkingLot;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class AllocationPanacheRepositoryTest {
    @Inject
    AllocationPanacheRepository allocationPanacheRepository;
    @Inject
    ParkingLotPanacheRepository parkingLotPanacheRepository;
    @Inject
    MemberPanacheRepository memberPanacheRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @AfterEach
    void tearDown() {
        allocationPanacheRepository.deleteAll();
        entityManager.flush();
    }

    @Transactional
    @Test
    void createAllocation_givenAllocationToSave_thenRepoContainsAllocationToSave() {
        // given
        Member member = memberPanacheRepository.getMemberById(1L).orElseThrow();
        ParkingLot parkingLot = parkingLotPanacheRepository.getAllParkingLots().stream().findFirst().orElseThrow();
        Allocation allocation = new Allocation(
                parkingLot,
                member,
                member.getLicencePlate().getLicensePlateNumber()
        );

        // when
        allocationPanacheRepository.createAllocation(allocation);
        entityManager.flush();

        // then
        Assertions
                .assertThat(allocationPanacheRepository.getAllAllocations())
                .containsExactlyInAnyOrder(allocation);
    }

    @Transactional
    @Test
    void getAllocationById() {
        // given
        Member member = memberPanacheRepository.getMemberById(1L).orElseThrow();
        ParkingLot parkingLot = parkingLotPanacheRepository.getAllParkingLots().stream().findFirst().orElseThrow();
        Allocation allocation = new Allocation(
                parkingLot,
                member,
                member.getLicencePlate().getLicensePlateNumber()
        );
        allocationPanacheRepository.createAllocation(allocation);
        entityManager.flush();

        // when
        Allocation actualAllocation = allocationPanacheRepository
                .getAllocationById(allocation.getId())
                .orElseThrow();
        entityManager.flush();

        // then
        Assertions
                .assertThat(actualAllocation)
                .isEqualTo(allocation);
    }

    @Transactional
    @Test
    void getAllAllocations_givenAllocationsAreCreated_thenReturnAllocations() {
        // given
        Member member1 = memberPanacheRepository.getMemberById(1L).orElseThrow();
        Member member2 = memberPanacheRepository.getMemberById(2L).orElseThrow();
        ParkingLot parkingLot = parkingLotPanacheRepository.getAllParkingLots().stream().findFirst().orElseThrow();

        Allocation allocation1 = new Allocation(
                parkingLot,
                member1,
                member1.getLicencePlate().getLicensePlateNumber()
        );
        Allocation allocation2 = new Allocation(
                parkingLot,
                member2,
                member2.getLicencePlate().getLicensePlateNumber()
        );

        allocationPanacheRepository.createAllocation(allocation1);
        allocationPanacheRepository.createAllocation(allocation2);
        entityManager.flush();

        // when
        Collection<Allocation> actualAllocations = allocationPanacheRepository
                .getAllAllocations();
        entityManager.flush();

        // then
        Assertions
                .assertThat(actualAllocations)
                .containsExactlyInAnyOrder(allocation1, allocation2);
    }
}