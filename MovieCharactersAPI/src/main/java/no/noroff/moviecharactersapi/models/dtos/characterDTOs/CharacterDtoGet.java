package no.noroff.moviecharactersapi.models.dtos.characterDTOs;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Movie;


import java.util.Set;

/**
 * The Character DTO class which is used to display all the fields in the Character table as reply to GET
 * requests done by the client.
 * @see Character
 */

@Data
public class CharacterDtoGet {

    private int id;

    private String name;

    private String alias;

    private String gender;

    private String picture;

    private Set<Integer> movies;
}

