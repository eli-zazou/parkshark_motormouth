package be.motormouth.division.services;

import be.motormouth.division.dto.DivisionDTO;
import be.motormouth.division.entities.Division;
import be.motormouth.division.entities.ListDivision;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class DivisionMapper {
    public static Collection<DivisionDTO> toDTO(Collection<Division> divisions) {
        return divisions.stream().map(DivisionMapper::toDTO).collect(Collectors.toList());
    }
    public static DivisionDTO toDTO(Division division){
        if (division.getParentDivision() == null) return new DivisionDTO(division.getId(), division.getName(), division.getOriginalName(), division.getDirector(),null);
        return new DivisionDTO(division.getId(), division.getName(), division.getOriginalName(), division.getDirector(), DivisionMapper.toDTO(division.getParentDivision()));
    }
    public static Division toDivision(DivisionDTO divisionDTO, Division mainDivision){
        return new Division(divisionDTO.name(), divisionDTO.originalName(), divisionDTO.director(), mainDivision);
    }
    public static ListDivision toList(Division division, List<Division> subdivisions){
        return new ListDivision(division.getId(), division.getName(), division.getOriginalName(), division.getDirector(), subdivisions);
    }
}
