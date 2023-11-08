package be.motormouth.allocation;

import be.motormouth.allocation.entities.Allocation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
public class AllocationPanacheRepository implements PanacheRepository<Allocation> {
    public Allocation createAllocation(Allocation allocation) {
        persist(allocation);
        return allocation;
    }

    public Optional<Allocation> getAllocationById(Long id) {
        return findByIdOptional(id);
    }

    public Collection<Allocation> getAllAllocations() {
        return listAll();
    }
}
