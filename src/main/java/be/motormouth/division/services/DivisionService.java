package be.motormouth.division.services;

import be.motormouth.division.DivisionPanacheRepository;
import be.motormouth.division.dto.DivisionDTO;
import be.motormouth.division.entities.Division;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import org.jboss.resteasy.reactive.RestHeader;

@ApplicationScoped
@Transactional
public class DivisionService {
    private final DivisionPanacheRepository divisionPanacheRepository;

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

    public Division createDivision(DivisionDTO divisionDTO) {
        return divisionPanacheRepository.createDivision(DivisionMapper.toDivision(divisionDTO));
    }
}
