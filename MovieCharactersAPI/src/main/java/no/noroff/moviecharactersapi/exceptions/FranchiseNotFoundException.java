package no.noroff.moviecharactersapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * The `FranchiseNotFoundException` class is a custom exception to indicate that a franchise
 * with a specified `id` was not found in the database.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class FranchiseNotFoundException extends RuntimeException{
    /**
     * Constructs a new `FranchiseNotFoundException` with a message indicating that the requested franchise with the specified `id` was not found in the database.
     * @param id The id of the franchise that was not found in the database.
     */
    public FranchiseNotFoundException(int id) {
        super("requested franchise with id: " + id + " was not found in the database");
    }
}
