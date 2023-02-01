package no.noroff.moviecharactersapi.mappers;


import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterSimpleDTO;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoGetSimple;
import org.mapstruct.Mapper;

import java.util.Collection;


@Mapper(componentModel = "spring")
public interface CharacterMapper {



    Collection<CharacterSimpleDTO> characterToCharacterSimpleDto(Collection<Character> character);


    CharacterDtoGetSimple characterToCharacterDtoSimple(Character character);
    Collection<CharacterDtoGetSimple>  characterToCharacterDtoSimple(Collection<Character> characters);

}
