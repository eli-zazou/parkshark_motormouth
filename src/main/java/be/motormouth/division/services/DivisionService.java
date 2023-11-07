package be.motormouth.division.services;

import be.motormouth.division.DivisionPanacheRepository;
import be.motormouth.division.dto.DivisionDTO;
import be.motormouth.division.dto.ListDivisionDTO;
import be.motormouth.division.entities.Division;
import be.motormouth.division.entities.ListDivision;
import be.motormouth.exceptions.UnknownDivisionException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

@ApplicationScoped
@Transactional
public class DivisionService {
    private final DivisionPanacheRepository divisionPanacheRepository;
    private final Logger logger = Logger.getLogger(DivisionService.class);
    private String errorMessage;

    public DivisionService(DivisionPanacheRepository divisionPanacheRepository) {
        this.divisionPanacheRepository = divisionPanacheRepository;
    }

    public Collection<Division> viewAllDivisions(String divisionId) {
        if (divisionId.equals("ALL")) return divisionPanacheRepository.getAllDivisions();
        return divisionPanacheRepository.getAllDivisions()
                .stream()
                .filter((division)-> division.getId() == Long.parseLong(divisionId))
                .collect(Collectors.toList());
    }
    public Collection<ListDivision> viewAllDivisionsTopDown() {
        divisionPanacheRepository.getMainDivisions()
                .stream()
                .map(division -> listAllSubdivisions(division))
                .collect(Collectors.toList());
        return null;//todo
    }
    private List<ListDivision> listAllSubdivisions(Division division) {
        return divisionPanacheRepository.getSubDivisions(division)
                .stream()
                .map(subdivision -> DivisionMapper.toList(division, listAllSubdivisions(subdivision)))
                .collect(Collectors.toList());
    }
    public Division viewDivisionsById(String divisionId) {
        return divisionPanacheRepository.findDivisionById(Long.parseLong(divisionId))
                .orElseThrow(()-> { errorMessage = "Division " + divisionId + " not found";
                    logger.errorf(errorMessage);
                    return new UnknownDivisionException(errorMessage);
                });
    }

    public Division createDivision(String id, DivisionDTO divisionDTO) {
        Division mainDivision = null;
        if (id != null) {
            Optional<Division> foundDivision = divisionPanacheRepository.findDivisionById(Long.parseLong(id));
            if (foundDivision.isEmpty()) throw new UnknownDivisionException("Division " + id + " not found");
            mainDivision = foundDivision.get();
        }
        return divisionPanacheRepository.createDivision(DivisionMapper.toDivision(divisionDTO, mainDivision));
    }
}
