package be.motormouth.allocation;

import be.motormouth.allocation.entities.Allocation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AllocationPanacheRepository implements PanacheRepository<Allocation> {
}
