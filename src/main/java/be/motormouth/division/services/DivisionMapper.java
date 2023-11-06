package be.motormouth.division.services;

import be.motormouth.division.dto.DivisionDTO;
import be.motormouth.division.entities.Division;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.stream.Collectors;

@ApplicationScoped
public class DivisionMapper {
    public static Collection<DivisionDTO> toDTO(Collection<Division> divisions) {
        return divisions.stream().map(DivisionMapper::toDTO).collect(Collectors.toList());
    }
    public static DivisionDTO toDTO(Division division){
        if (division.getMainDivision() == null) return new DivisionDTO(division.getId(), division.getName(), division.getOriginalName(), division.getDirector(),null);
        return new DivisionDTO(division.getId(), division.getName(), division.getOriginalName(), division.getDirector(), DivisionMapper.toDTO(division.getMainDivision()));
    }
    public static Division toDivision(DivisionDTO divisionDTO){
        return new Division(divisionDTO.name(), divisionDTO.originalName(), divisionDTO.director(), DivisionMapper.toDivision(divisionDTO.subDivision()));
    }
}
