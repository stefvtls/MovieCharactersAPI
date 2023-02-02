package no.noroff.moviecharactersapi.models.dtos.movieDTOs;

import lombok.Getter;
import lombok.Setter;
import no.noroff.moviecharactersapi.models.Movie;

import java.util.Set;

/**
 * The Movie DTO class which is used to display all the fields in the Movie table as reply to GET
 * requests done by the client.
 * @see Movie
 */

@Getter
@Setter
public class MovieDtoGet {
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
