package be.motormouth.parkinglot;

import be.motormouth.parkinglot.entities.ParkingLot;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ParkingLotPanacheRepository implements PanacheRepository<ParkingLot> {
    public ParkingLot createParkingLot(ParkingLot parkingLot) {

        persist(parkingLot);
        return parkingLot;
    }

    public ParkingLot getParkingLotByName(String name) {
        return find("name", name).firstResult();
    }
}
