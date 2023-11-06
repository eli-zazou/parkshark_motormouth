package be.motormouth.parkinglot;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ParkingLotService {
    ParkingLotRepository parkingLotRepository;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }
}
