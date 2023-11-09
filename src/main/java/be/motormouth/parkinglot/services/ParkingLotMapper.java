package be.motormouth.parkinglot.services;

import be.motormouth.division.entities.Division;
import be.motormouth.division.services.DivisionMapper;
import be.motormouth.parkinglot.dtos.CreateParkingLotDto;
import be.motormouth.parkinglot.dtos.ListParkingLotDto;
import be.motormouth.parkinglot.dtos.ParkingLotDto;
import be.motormouth.parkinglot.entities.ParkingLot;

import java.util.List;

public class ParkingLotMapper {
    public static List<ListParkingLotDto> toListDto(List<ParkingLot> parkingLots) {
        return parkingLots.stream().map(ParkingLotMapper::toListDto).toList();
    }
    public static ListParkingLotDto toListDto(ParkingLot parkingLot) {
        return new ListParkingLotDto(parkingLot.getId(),
                parkingLot.getName(),
                parkingLot.getCapacity(),
                parkingLot.getContactPerson().getEmail(),
                (parkingLot.getContactPerson().getPhoneNumber() == null) ? parkingLot.getContactPerson().getMobilePhoneNumber() : parkingLot.getContactPerson().getPhoneNumber());
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
                DivisionMapper.toListDTO(parkingLot.getDivision()));
    }

    public static ParkingLot toEntity(CreateParkingLotDto createParkingLotDto, Division division){
        return new ParkingLot(createParkingLotDto.name(),
                createParkingLotDto.category(),
                createParkingLotDto.capacity(),
                ContactPersonMapper.toEntity(createParkingLotDto.createContactPersonDto()),
                division,
                createParkingLotDto.address(),
                createParkingLotDto.pricePerHourInEuro(),
                createParkingLotDto.numberOfPlacesAvailable());
    }
}
