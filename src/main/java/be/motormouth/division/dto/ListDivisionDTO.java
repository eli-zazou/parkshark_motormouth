package be.motormouth.division.dto;

import java.util.Collection;

public record ListDivisionDTO(Long id, String name, String originalName, String director, Collection<DivisionDTO> subDivisions) {

}
