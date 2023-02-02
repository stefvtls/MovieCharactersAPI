package no.noroff.moviecharactersapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The `MovieNotFoundException` class is a custom exception to indicate that a movie
 * with a specified `id` was not found in the database.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends RuntimeException {
    /**
     * Constructs a new `MovieNotFoundException` with a message indicating that the requested movie with the specified `id` was not found in the database.
     * @param id The id of the movie that was not found in the database.
     */
    public MovieNotFoundException(int id) {
        super("requested movie with id: " + id + " was not found in the database");
    }
}