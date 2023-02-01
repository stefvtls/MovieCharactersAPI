package no.noroff.moviecharactersapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Franchise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=100, nullable=false)
    private String name;

    @Column(length=300)
    private String description;


    @OneToMany(mappedBy="franchise")
    private Set<Movie> movies;

}