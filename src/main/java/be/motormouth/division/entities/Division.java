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
    private Division subDivision;

    public Division() {

    }

}
