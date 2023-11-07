package be.motormouth.parkinglot.services;

import be.motormouth.parkinglot.ParkingLotPanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ParkingLotService {
    ParkingLotPanacheRepository parkingLotPanacheRepository;

    public ParkingLotService(ParkingLotPanacheRepository parkingLotPanacheRepository) {
        this.parkingLotPanacheRepository = parkingLotPanacheRepository;
    }
}
