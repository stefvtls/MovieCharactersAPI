package no.noroff.moviecharactersapi.mappers;

import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.models.dtos.movieDTOs.MovieDtoGet;
import no.noroff.moviecharactersapi.models.dtos.movieDTOs.MovieDtoPost;
import no.noroff.moviecharactersapi.models.dtos.movieDTOs.MovieDtoPut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import no.noroff.moviecharactersapi.models.dtos.movieDTOs.MovieDtoGetSimple;

/**
 The MovieMapper interface provides mapping functions for conversion between {@link Movie} and its DTOs ({@link MovieDtoGet}, {@link MovieDtoGetSimple},
 {@link MovieDtoPost}, and {@link MovieDtoPut}).
 The mapping is done with MapStruct, a code generator for bean mapping.
 @see Movie
 @see MovieDtoGet
 @see MovieDtoGetSimple
 @see MovieDtoPost
 @see MovieDtoPut
 */
@Mapper(componentModel = "spring")
public interface MovieMapper {


    /**
     Maps a {@link Movie} to a {@link MovieDtoGet}.
     The mapping also includes the conversion of the Set of {@link Character} to a Set of character IDs (integers).
     @param movie - the movie to be mapped
     @return a movie DTO
     */
    @Mapping(target = "franchise", source = "franchise.id")
    @Mapping(target = "characters", qualifiedByName = "charactersToCharactersId")
    MovieDtoGet movieToMovieDto(Movie movie);

    /**
     Maps a Collection of {@link Movie} to a Collection of {@link MovieDtoGet}.
     The mapping also includes the conversion of the Set of {@link Character} to a Set of character IDs (integers).
     @param movies - the collection of movies to be mapped
     @return a collection of movie DTOs
     */
    Collection<MovieDtoGet> movieToMovieDto(Collection<Movie> movies);

    /**
     Maps a {@link Movie} to a {@link MovieDtoGetSimple}.
     @param movie - the movie to be mapped
     @return a movie DTO
     */
    MovieDtoGetSimple movieToMovieDtoSimple(Movie movie);

    /**
     Maps a Collection of {@link Movie} to a Collection of {@link MovieDtoGetSimple}.
     @param movies - the collection of movies to be mapped
     @return a collection of movie DTOs
     */
    Collection<MovieDtoGetSimple> movieToMovieDtoSimple(Collection<Movie> movies);

    /**
     Maps a {@link MovieDtoPost} to a {@link Movie}.
     @param movieDtoPost - the movie DTO to be mapped
     @return a movie
     */
    Movie moviePostDtoToMovie(MovieDtoPost movieDtoPost);

    /**
     Maps a {@link MovieDtoPut} to a {@link Movie}.
     @param movieDtoPut - the movie DTO to be mapped
     @return a movie
     */
    Movie moviePutDtoToMovie(MovieDtoPut movieDtoPut);



    /**
     Maps a set of {@link Character} objects to their corresponding character IDs.
     @param value The set of characters to map.
     @return The set of character IDs corresponding to the characters in the input set.
     */
    @Named(value = "charactersToCharactersId")
    default Set<Integer> map(Set<Character> value){
        if(value == null)
            return null;
        return value.stream().map(c -> c.getId()).collect(Collectors.toSet());
    }


}
