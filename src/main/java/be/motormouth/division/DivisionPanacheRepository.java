package be.motormouth.division;

import be.motormouth.division.entities.Division;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class DivisionPanacheRepository implements PanacheRepository<Division> {
    private final Logger logger = Logger.getLogger(DivisionPanacheRepository.class);

    public Collection<Division> getAllDivisions() {
        return listAll();
    }
    public Optional<Division> findDivisionById(Long id) {
        return Optional.ofNullable(findById(id));
    }
    public Division createDivision(Division division) {
        persist(division);
        return division;
    }
}