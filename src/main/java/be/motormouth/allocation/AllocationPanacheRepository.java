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

    public Collection<Allocation> getAllAllocationsForMemberId(Long id) {
        return find("member.id", id).list();
    }

    public Collection<Allocation> getActiveAllocationsForMemberId(Long id) {
        return find("member.id = ?1 and endTime is null", id).list();
    }

    public Collection<Allocation> getStoppedAllocationsForMemberId(Long id) {
        return find("member.id = ?1 and endTime is not null", id).list();
    }
}
