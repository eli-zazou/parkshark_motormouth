package be.motormouth;

import be.motormouth.division.DivisionPanacheRepository;
import be.motormouth.division.dto.DivisionDTO;
import be.motormouth.division.entities.Division;
import be.motormouth.division.services.DivisionMapper;
import be.motormouth.division.services.DivisionService;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DivisionServiceTest {
    private DivisionService divisionService;
    private DivisionPanacheRepository divisionPanacheRepository;
    //
    private Division parentDivision, childDivision;
    private DivisionDTO parentDivisionDTO, childDivisionDTO;

    @BeforeEach
    void setUp() {
        divisionPanacheRepository = Mockito.mock(DivisionPanacheRepository.class);
      //divisionPanacheRepository = new DivisionPanacheRepository();
        divisionService = new DivisionService(divisionPanacheRepository);
      //
        parentDivision = new Division("Parent", "Original", "Director", null);
        parentDivisionDTO = DivisionMapper.toDTO(parentDivision);
        childDivision = new Division("Child", "Original", "Director", parentDivision);
        childDivisionDTO = DivisionMapper.toDTO(childDivision);
    }
    @Test
    void viewAllDivisions() {
        Mockito.when(divisionPanacheRepository.getAllDivisions()).thenReturn(Arrays.asList(parentDivision,childDivision));
        Assertions.assertEquals(divisionService.viewAllDivisions("ALL"),Arrays.asList(parentDivision,childDivision));
    }
//    @Test
//    void createDivision() {
//        Mockito.when(divisionPanacheRepository.createDivision(DivisionMapper.toDivision(parentDivisionDTO, null))).thenReturn(parentDivision);
//        Division result = divisionService.createDivision(null, parentDivisionDTO);
//        Assertions.assertEquals(result, parentDivision);
//    }
}