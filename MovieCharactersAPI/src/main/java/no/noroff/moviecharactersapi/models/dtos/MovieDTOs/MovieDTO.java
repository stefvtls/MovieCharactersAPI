package no.noroff.moviecharactersapi.models.dtos.MovieDTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MovieDTO {
    private int id;
    private String title;
    private String director;
    private String genre;
    private int releaseYear;
    private String moviePoster;
    private String trailer;
    private int franchise;
    private Set<Integer> characters;

}
