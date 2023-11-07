package be.motormouth.parkinglot;

import be.motormouth.exceptions.UnknownParkingLotException;
import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ParkingLotService {
    private final ParkingLotRepository parkingLotRepository;
    private final Logger logger = Logger.getLogger(ParkingLotService.class);
    private String errorMessage;

    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLot getParkingLot(Long id) {
        return parkingLotRepository.findByIdOptional(id)
                .orElseThrow(() -> {
                    errorMessage = "Parking lot " + id.toString() + " not found";
                    logger.info(errorMessage);
                    return new UnknownParkingLotException(errorMessage);
                });
    }
}
