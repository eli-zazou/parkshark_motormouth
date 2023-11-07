package be.motormouth.division.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "DIVISION")
public class Division {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "division_seq")
    @SequenceGenerator(name = "division_seq", sequenceName = "division_seq", allocationSize = 1)
    private long id;
    private String name;
    private String originalName;
    private String director;
    @ManyToOne
    @JoinColumn(name = "FK_DIVISION_ID")
    private Division parentDivision;

    public Division() {
    }

    public Division(String name, String originalName, String director, Division parentDivision) {
        this.name = name;
        this.originalName = originalName;
        this.director = director;
        this.parentDivision = parentDivision;
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

    public Division getParentDivision() {
        return parentDivision;
    }
}
