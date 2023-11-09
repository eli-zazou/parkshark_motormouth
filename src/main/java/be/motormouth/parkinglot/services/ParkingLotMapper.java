package be.motormouth.parkinglot.services;

import be.motormouth.division.dto.DivisionDTO;
import be.motormouth.division.entities.Division;
import be.motormouth.division.services.DivisionMapper;
import be.motormouth.parkinglot.dtos.CreateParkingLotDto;
import be.motormouth.parkinglot.dtos.ParkingLotDto;
import be.motormouth.parkinglot.entities.ParkingLot;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotMapper {
    public static List<ParkingLotDto> toDto(List<ParkingLot> parkingLots) {
        return parkingLots.stream().map(ParkingLotMapper::toDto).toList();
    }
    public static ParkingLotDto toDto(ParkingLot parkingLot) {
        return new ParkingLotDto(parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getCategory(),
                parkingLot.getCapacity(),
                parkingLot.getContactPerson(),
                parkingLot.getAddress(),
                parkingLot.getPricePerHourInEuro(),
                parkingLot.getNumberOfPlacesAvailable(),
                DivisionMapper.toDTO(parkingLot.getDivision()));
    }

    public static ParkingLot toEntity(CreateParkingLotDto createParkingLotDto, Division division){
        return new ParkingLot(createParkingLotDto.name(),
                createParkingLotDto.category(),
                createParkingLotDto.capacity(),
                ContactPersonMapper.toEntity(createParkingLotDto.createContactPersonDto()),
                createParkingLotDto.address(),
                createParkingLotDto.pricePerHourInEuro(),
                createParkingLotDto.numberOfPlacesAvailable(),
                division);
    }
}
