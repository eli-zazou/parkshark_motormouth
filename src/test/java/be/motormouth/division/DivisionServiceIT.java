package be.motormouth.division;

import be.motormouth.division.DivisionPanacheRepository;
import be.motormouth.division.dto.DivisionDTO;
import be.motormouth.division.entities.Division;
import be.motormouth.division.services.DivisionService;
import be.motormouth.exceptions.UnknownDivisionException;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
public class DivisionServiceIT {
    private Division parentDivision, childDivision;
    private DivisionDTO parentDivisionDTO, childDivisionDTO;
    @Inject
    DivisionPanacheRepository divisionPanacheRepository;
    @Inject
    DivisionService divisionService;
    @BeforeEach
    void setUp() {
        parentDivisionDTO = new DivisionDTO(1L,"Parent", "Original", "Director", null);
        childDivisionDTO = new DivisionDTO(1L,"Child", "Original", "Director", null);
    }
    @Test
    void viewAllDivisions(){
        Collection<Division> result = divisionService.viewAllDivisions("ALL");
        Assertions.assertEquals(5, result.size());
    }
    @Test
    void viewAllDivisionsWithQueryParam(){
        Collection<Division> result = divisionService.viewAllDivisions("1");
        Assertions.assertEquals(1, result.size());
    }
    @Test
    void viewDivisionsById() {
        Division result = divisionService.viewDivisionsById("1");
        Assertions.assertEquals("Top Level", result.getName());
    }
    @Test
    void viewDivisionsByIdWhenIdNotFound() {
        RuntimeException thrown = Assertions.assertThrows(UnknownDivisionException.class
                , () -> divisionService.viewDivisionsById("10"));
        Assertions
                .assertEquals("Division 10 not found", thrown.getMessage());
    }
    @Test
    void createDivision() {
        Division result = divisionService.createDivision(null, parentDivisionDTO);
        Assertions.assertEquals(parentDivisionDTO.name(), result.getName());
    }
    @Test
    void createSubDivision() {
        Division result = divisionService.createDivision("1", childDivisionDTO);
        Assertions.assertEquals(childDivisionDTO.name(), result.getName());
        Assertions.assertEquals(1L, result.getParentDivision().getId());
    }
    @Test
    void createSubDivisionWithNonExistantParent() {
        RuntimeException thrown = Assertions.assertThrows(UnknownDivisionException.class
                , () ->divisionService.createDivision("10", childDivisionDTO));
        Assertions
                .assertEquals("Division 10 not found", thrown.getMessage());
    }
}
