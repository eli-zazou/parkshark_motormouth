package be.motormouth.division;

import be.motormouth.division.entities.Division;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.emptyMap;

@ApplicationScoped
public class DivisionPanacheRepository implements PanacheRepository<Division> {
    private final Logger logger = Logger.getLogger(DivisionPanacheRepository.class);

    public Collection<Division> getAllDivisions() {
        return listAll();
    }
    public Collection<Division> getMainDivisions() {
        return find("parentDivision = null")
                .stream()
                .toList();
    }
    public Collection<Division> getSubDivisions(Division division) {
        return find("parentDivision = ?1", division)
                .stream()
                .toList();
    }
    public Optional<Division> findDivisionById(Long id) {
        return findByIdOptional(id);
    }
    public Division createDivision(Division division) {
        persist(division);
        return division;
    }
}