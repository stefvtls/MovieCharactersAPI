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
    @JsonGetter("movies")
    public List<Integer> moviesGetter() {
        if(movies == null)
            return null;
        return movies.stream().map(m -> m.getId()).collect(Collectors.toList());
    }




}