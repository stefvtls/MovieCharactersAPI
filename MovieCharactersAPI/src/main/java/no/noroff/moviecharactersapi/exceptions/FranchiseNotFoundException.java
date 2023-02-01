package no.noroff.moviecharactersapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class FranchiseNotFoundException extends RuntimeException{
    public FranchiseNotFoundException(int id) {
        super("requested franchise with id: " + id + " was not found in the database");
    }
}
