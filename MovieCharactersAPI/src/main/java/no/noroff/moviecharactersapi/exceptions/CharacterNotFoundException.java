package no.noroff.moviecharactersapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



/**
 * The `CharacterNotFoundException` class is a custom exception to indicate that a character
 * with a specified `id` was not found in the database.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CharacterNotFoundException extends RuntimeException{
    /**
     * Constructs a new `CharacterNotFoundException` with a message indicating that the requested character with the specified `id` was not found in the database.
     * @param id The id of the character that was not found in the database.
     */
    public CharacterNotFoundException(int id) {
        super("requested character with id: "+ id + "was not found in the database.");
    }
}
