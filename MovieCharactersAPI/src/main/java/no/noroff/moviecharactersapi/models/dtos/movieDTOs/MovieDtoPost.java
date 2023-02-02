package no.noroff.moviecharactersapi.models.dtos.movieDTOs;

import lombok.Getter;
import lombok.Setter;
import no.noroff.moviecharactersapi.models.Movie;

/**
 * The Movie DTO class which is used for when a user want to add a new franchise to the database. Only the movie title
 * is required as ids are auto-generated. Other fields for a movie have to be updated with PUT requests.
 * @see Movie
 * @see MovieDtoPut
 */
@Getter
@Setter
public class MovieDtoPost {
    private String title;
}
