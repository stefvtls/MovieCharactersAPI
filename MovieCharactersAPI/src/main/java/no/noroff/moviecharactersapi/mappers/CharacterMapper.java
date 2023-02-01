package no.noroff.moviecharactersapi.mappers;



import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoGet;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoGetSimple;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface CharacterMapper {

//    SIMPLE DTO GET
    CharacterDtoGetSimple characterToCharacterDtoSimple(Character character);
    Collection<CharacterDtoGetSimple>  characterToCharacterDtoSimple(Collection<Character> characters);

//    DTO GET
    @Mapping(target="movies", qualifiedByName = "mapSetOfMoviesToSetOfIntegers")
    CharacterDtoGet characterToCharacterDto(Character character);
    @Mapping(target="movies", qualifiedByName = "mapSetOfMoviesToSetOfIntegers")
    Collection<CharacterDtoGet> characterToCharacterDto(Collection<Character> characters);


    @Named("mapSetOfMoviesToSetOfIntegers")
    default Set<Integer> mapSetOfMoviesToSetOfIntegers(Set<Movie> movies){
        if (movies == null) {
            return null;
        }
        return movies.stream().map(Movie::getId).collect(Collectors.toSet());
    }


    // POST DTO



    // PUT DTO



}
