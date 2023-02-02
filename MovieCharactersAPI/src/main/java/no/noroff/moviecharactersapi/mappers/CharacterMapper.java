package no.noroff.moviecharactersapi.mappers;



import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoGet;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoGetSimple;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoPost;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoPut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**

 CharacterMapper interface is used to map between Character model and different character DTOs.
 The mapper uses MapStruct framework for mapping between models.
 @see Character
 @see CharacterDtoGet
 @see CharacterDtoGetSimple
 @see CharacterDtoPost
 @see CharacterDtoPut
 */
@Mapper(componentModel = "spring")
public interface CharacterMapper {


    /**
     Maps Character model to CharacterDtoGetSimple DTO
     @param character Character model to be mapped
     @return mapped CharacterDtoGetSimple DTO
     */
    CharacterDtoGetSimple characterToCharacterDtoSimple(Character character);


    /**
     Maps Collection of Character models to Collection of CharacterDtoGetSimple DTOs
     @param characters Collection of Character models to be mapped
     @return Collection of mapped CharacterDtoGetSimple DTOs
     */
    Collection<CharacterDtoGetSimple>  characterToCharacterDtoSimple(Collection<Character> characters);


    /**
     Maps Character model to CharacterDtoGet DTO
     @param character Character model to be mapped
     @return mapped CharacterDtoGet DTO
     */
    @Mapping(target="movies", qualifiedByName = "mapSetOfMoviesToSetOfIntegers")
    CharacterDtoGet characterToCharacterDto(Character character);



    /**
     Maps Collection of Character models to Collection of CharacterDtoGet DTOs
     @param characters Collection of Character models to be mapped
     @return Collection of mapped CharacterDtoGet DTOs
     */
    @Mapping(target="movies", qualifiedByName = "mapSetOfMoviesToSetOfIntegers")
    Collection<CharacterDtoGet> characterToCharacterDto(Collection<Character> characters);



    /**
     Maps set of Movie models to set of Integer ids of movies
     @param movies Set of Movie models to be mapped
     @return Set of ids of movies
     */
    @Named("mapSetOfMoviesToSetOfIntegers")
    default Set<Integer> mapSetOfMoviesToSetOfIntegers(Set<Movie> movies){
        if (movies == null) {
            return null;
        }
        return movies.stream().map(Movie::getId).collect(Collectors.toSet());
    }



    /**
     Maps CharacterDtoPost DTO to Character model
     @param characterDtoPost CharacterDtoPost DTO to be mapped
     @return mapped Character model
     */
    Character characterDtoPostToCharacter(CharacterDtoPost characterDtoPost);



    /**
     Maps CharacterDtoPut DTO to Character model
     @param characterDtoPut CharacterDtoPut DTO to be mapped
     @return mapped Character model
     */
    Character characterDtoPutToCharacter(CharacterDtoPut characterDtoPut);



}
