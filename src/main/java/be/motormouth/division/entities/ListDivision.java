package be.motormouth.division.entities;

import java.util.List;

public class ListDivision {
    private long id;
    private String name;
    private String originalName;
    private String director;
    private List<ListDivision> subDivisions;

    public ListDivision(long id, String name, String originalName, String director, List<ListDivision> subDivisions) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.director = director;
        this.subDivisions = subDivisions;
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

    public List<ListDivision> getSubDivisions() {
        return subDivisions;
    }

    @Override
    public String toString() {
        return "ListDivision{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", originalName='" + originalName + '\'' +
                ", director='" + director + '\'' +
                ", subDivisions=" + subDivisions +
                '}';
    }
}
