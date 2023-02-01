package no.noroff.moviecharactersapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CharacterNotFoundException extends RuntimeException{
    public CharacterNotFoundException(int id) {
        super("requested character with id: "+ id + "was not found in the database.");
    }
}
