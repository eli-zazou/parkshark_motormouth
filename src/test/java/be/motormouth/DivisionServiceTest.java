package be.motormouth;

import be.motormouth.division.DivisionPanacheRepository;
import be.motormouth.division.dto.DivisionDTO;
import be.motormouth.division.entities.Division;
import be.motormouth.division.services.DivisionMapper;
import be.motormouth.division.services.DivisionService;
import be.motormouth.exceptions.UnknownDivisionException;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class DivisionServiceTest {
    private DivisionService divisionService;
    private DivisionPanacheRepository divisionPanacheRepository;
    //
    private Division parentDivision, childDivision;
    private DivisionDTO parentDivisionDTO, childDivisionDTO;

    @BeforeEach
    void setUp() {
        divisionPanacheRepository = Mockito.mock(DivisionPanacheRepository.class);
        divisionService = new DivisionService(divisionPanacheRepository);
      //
        parentDivision = new Division(1,"Parent", "Original", "Director", null);
        parentDivisionDTO = DivisionMapper.toDTO(parentDivision);
        childDivision = new Division(2,"Child", "Original", "Director", parentDivision);
        childDivisionDTO = DivisionMapper.toDTO(childDivision);
    }
    @Test
    void viewAllDivisions() {
        Mockito
                .when(divisionPanacheRepository.getAllDivisions())
                .thenReturn(Arrays.asList(parentDivision,childDivision));
        Assertions.assertEquals(divisionService.viewAllDivisions("ALL"),Arrays.asList(parentDivision,childDivision));
    }
    @Test
    void viewAllDivisionsWithQueryParam() {
        Mockito
                .when(divisionPanacheRepository.getAllDivisions())
                .thenReturn(Collections.singletonList(parentDivision));
        Assertions.assertEquals(divisionService.viewAllDivisions("1"), Collections.singletonList(parentDivision));
    }
    @Test
    void viewDivisionsById() {
        Mockito
                .when( divisionPanacheRepository.findDivisionById(2L))
                .thenReturn(Optional.of(childDivision));
        Assertions.assertEquals(divisionService.viewDivisionsById("2"),childDivision);
    }
    @Test
    void viewDivisionsByIdWhenIdNotFound() {
        RuntimeException thrown = Assertions.assertThrows(UnknownDivisionException.class
                , () -> divisionService.viewDivisionsById("1"));
        Assertions
                .assertEquals("Division 1 not found", thrown.getMessage());
    }
    @Test
    void createDivision() {
        Mockito
                .when(divisionPanacheRepository.createDivision(any(Division.class)))
                .thenReturn(parentDivision);
        Division result = divisionService.createDivision(null, parentDivisionDTO);
        Assertions.assertEquals(result, parentDivision);
    }
    @Test
    void createSubDivision() {
        Mockito
                .when(divisionPanacheRepository.createDivision(any(Division.class)))
                .thenReturn(childDivision);
        Mockito
                .when( divisionPanacheRepository.findDivisionById(1L))
                .thenReturn(Optional.of(parentDivision));
        Division result = divisionService.createDivision("1", childDivisionDTO);
        Assertions.assertEquals(result, childDivision);
    }
    @Test
    void createSubDivisionWhenParentNotFound() {
        Mockito
                .when(divisionPanacheRepository.createDivision(any(Division.class)))
                .thenReturn(childDivision);
        RuntimeException thrown = Assertions.assertThrows(UnknownDivisionException.class
                , () ->divisionService.createDivision("1", childDivisionDTO));
        Assertions
                .assertEquals("Division 1 not found", thrown.getMessage());
    }
}