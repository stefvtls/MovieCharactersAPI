package no.noroff.moviecharactersapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 100)
    private String genre;


    private int releaseYear;

    @Column(length = 50, nullable = false)
    private String director;

    private String moviePoster;

    private String trailer;


    @ManyToOne
    @JoinColumn
    private Franchise franchise;


    @ManyToMany
    private Set<Character> characters;
}
