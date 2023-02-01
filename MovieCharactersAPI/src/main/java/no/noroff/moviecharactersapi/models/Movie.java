package no.noroff.moviecharactersapi.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Column(length = 50)
    private String director;

    private String moviePoster;

    private String trailer;


    @ManyToOne
    @JoinColumn
    private Franchise franchise;

    @ManyToMany
    private Set<Character> characters;

}
