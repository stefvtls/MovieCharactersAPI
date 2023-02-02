package no.noroff.moviecharactersapi.models.dtos.movieDTOs;

import lombok.Getter;
import lombok.Setter;
import no.noroff.moviecharactersapi.models.Movie;

/**
 * The Movie DTO class which is used for when a user want to update a movie in the database. The user has to
 * provide the id for the movie that needs to be updated, as well as all the fields in the movie table (could be
 * left null)
 * @see Movie
 */
@Getter
@Setter
public class MovieDtoPut {
    private int id;
    private String title;
    private String director;
    private String genre;
    private int releaseYear;
    private String moviePoster;
    private String trailer;
}
