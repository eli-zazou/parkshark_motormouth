package be.motormouth.division.entities;

import java.util.List;

public class ListDivision {
    private long id;
    private String name;
    private String originalName;
    private String director;
    private List<Division> subDivisions;

    public ListDivision() {
    }

    public ListDivision(long id, String name, String originalName, String director, List<Division> subDivision) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.director = director;
        this.subDivisions = subDivision;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getDirector() {
        return director;
    }

    public List<Division> getSubDivisions() {
        return subDivisions;
    }
}
