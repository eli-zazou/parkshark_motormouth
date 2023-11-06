package be.motormouth.division;

import be.motormouth.division.entities.Division;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DivisionPanacheRepository implements PanacheRepository<Division> {
}
