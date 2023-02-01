package no.noroff.moviecharactersapi.mappers;


import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.models.dto.character.CharacterSimpleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CharacterMapper {


    Collection<CharacterSimpleDTO> characterToCharacterSimpleDto(Collection<Character> character);

    @Named("moviesToIds")
    default Set<Integer> map(Set<Movie> source) {
        if (source == null) return null;
        return source.stream().map(m -> m.getId()
        ).collect(Collectors.toSet());
    }

}
