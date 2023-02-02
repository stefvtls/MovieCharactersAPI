package no.noroff.moviecharactersapi.models.dtos.characterDTOs;


import lombok.Data;
import no.noroff.moviecharactersapi.models.Character;

/**
 * The Character DTO class which is used for when a user want to update a character in the database. The user has to
 * provide the id for the character that needs to be updated, as well as all the fields in the character table (could be
 * left null)
 * @see Character
 */

@Data
public class CharacterDtoPut {

    private int id;
    private String name;
    private String alias;
    private String gender;
    private String picture;
}
