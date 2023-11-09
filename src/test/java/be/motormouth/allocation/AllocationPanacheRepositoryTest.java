package be.motormouth.allocation;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static be.motormouth.testConstants.AllocationTestConstants.ALLOCATION_1;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class AllocationPanacheRepositoryTest {
    @Inject
    AllocationPanacheRepository allocationPanacheRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Test
    void createAllocation_givenAllocationToSave_thenRepoContainsAllocationToSave() {
        // given

        // when
        allocationPanacheRepository.createAllocation(ALLOCATION_1);
        entityManager.flush();

        // then
        Assertions
                .assertThat(allocationPanacheRepository.getAllAllocations())
                .containsExactlyInAnyOrder(ALLOCATION_1);
    }

    @Test
    void getAllocationById() {
    }

    @Test
    void getAllAllocations() {
    }
}