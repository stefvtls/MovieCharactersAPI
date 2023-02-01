package no.noroff.moviecharactersapi.mappers;

import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoGetSimple;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    CharacterDtoGetSimple characterToCharacterDtoSimple(Character character);
}
