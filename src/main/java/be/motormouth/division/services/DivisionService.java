package be.motormouth.division.services;

import be.motormouth.division.DivisionPanacheRepository;
import be.motormouth.division.dto.DivisionDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.Collection;
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

    public Collection<DivisionDTO> viewAllDivisions(String divisionId) {
        throw new RuntimeException("Not Implemented");
    }

    public DivisionDTO createDivision(DivisionDTO divisionDTO) {
        throw new RuntimeException("Not Implemented");
    }
}
