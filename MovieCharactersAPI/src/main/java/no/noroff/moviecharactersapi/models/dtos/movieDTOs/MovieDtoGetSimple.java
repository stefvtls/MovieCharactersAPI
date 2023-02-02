package no.noroff.moviecharactersapi.models.dtos.movieDTOs;

import lombok.Data;
import no.noroff.moviecharactersapi.models.Movie;

/**
 * The Movie DTO class which is used for simple displays in nested structures as reply to GET requests done by the
 * client when only the id is relevant.
 * @see Movie
 */
@Data
public class MovieDtoGetSimple {
    private int id;
}
