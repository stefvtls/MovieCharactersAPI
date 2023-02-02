package no.noroff.moviecharactersapi.models.dtos.characterDTOs;

import lombok.Data;
import no.noroff.moviecharactersapi.models.Character;


/**
 * The Character DTO class which is used for when a user want to add a new character to the database. Only the name is
 * required as ids are auto-generated. Other fields for a character have to be updated with PUT requests.
 * @see Character
 * @see CharacterDtoPut
 */

@Data
public class CharacterDtoPost {

    private String name;

}
