package no.noroff.moviecharactersapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length=100, nullable = false)
    private String name;

    @Column(length=50)
    private String alias;

    @Column(length=10)
    private String gender;

    @Column
    private String picture;





    @ManyToMany(mappedBy="characters")
    private Set<Movie> movies;





}