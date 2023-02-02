package no.noroff.moviecharactersapi.models.dtos.characterDTOs;

import lombok.Data;
import no.noroff.moviecharactersapi.models.Character;

/**
 * The Character DTO class which is used for simple displays in nested structures as reply to GET requests done by the
 * client when only the id is relevant.
 * @see Character
 */

@Data
public class CharacterDtoGetSimple {
    private int id;

}
