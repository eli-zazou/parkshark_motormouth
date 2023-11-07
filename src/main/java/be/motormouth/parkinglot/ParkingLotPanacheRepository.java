package be.motormouth.parkinglot;

import be.motormouth.parkinglot.entities.ParkingLot;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ParkingLotPanacheRepository implements PanacheRepository<ParkingLot> {
}
