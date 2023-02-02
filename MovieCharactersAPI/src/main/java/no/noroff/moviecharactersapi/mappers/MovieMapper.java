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


@Mapper(componentModel = "spring")
public interface MovieMapper {


    //GET FULL
    @Mapping(target = "franchise", source = "franchise.id")
    @Mapping(target = "characters", qualifiedByName = "charactersToCharactersId")
    MovieDtoGet movieToMovieDto(Movie movie);

    Collection<MovieDtoGet> movieToMovieDto(Collection<Movie> movies);

    //GET SIMPLE
    MovieDtoGetSimple movieToMovieDtoSimple(Movie movie);
    Collection<MovieDtoGetSimple> movieToMovieDtoSimple(Collection<Movie> movies);

    //POST
    Movie moviePostDtoToMovie(MovieDtoPost movieDtoPost);

    //PUT
    Movie moviePutDtoToMovie(MovieDtoPut movieDtoPut);




    @Named(value = "charactersToCharactersId")
    default Set<Integer> map(Set<Character> value){
        if(value == null)
            return null;
        return value.stream().map(c -> c.getId()).collect(Collectors.toSet());
    }


}
