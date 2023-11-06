package be.motormouth.division.dto;

public record DivisionDTO (Long id, String name, String originalName, String director, DivisionDTO subDivision) {
}
