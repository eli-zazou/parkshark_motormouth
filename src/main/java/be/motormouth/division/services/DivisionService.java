package be.motormouth.division.services;

import be.motormouth.division.DivisionPanacheRepository;
import be.motormouth.division.dto.DivisionDTO;
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
                .filter((division) -> division.getId() == Long.parseLong(divisionId))
                .collect(Collectors.toList());
    }

    public List<ListDivision> viewAllDivisionsTopDown() {
        return divisionPanacheRepository.getMainDivisions()
                .stream()
                .map(division -> DivisionMapper.toListDivision(division, getAllSubdivisions(division)))
                .collect(Collectors.toList());
    }

    private List<ListDivision> getAllSubdivisions(Division division) {
        try {
            return divisionPanacheRepository.getSubDivisions(division)
                    .stream()
                    .map(subdivision -> DivisionMapper.toListDivision(subdivision, getAllSubdivisions(subdivision)))
                    .collect(Collectors.toList());
        }
        catch (Exception e) {
            return null;
        }
    }

    public Division viewDivisionsById(String divisionId) {
        return divisionPanacheRepository.findDivisionById(Long.parseLong(divisionId))
                .orElseThrow(() -> {
                    errorMessage = "Division " + divisionId + " not found";
                    logger.info(errorMessage);
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
