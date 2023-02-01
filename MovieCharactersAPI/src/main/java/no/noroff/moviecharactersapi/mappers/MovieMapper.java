package no.noroff.moviecharactersapi.mappers;

import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.models.dtos.MovieDTOs.MovieDTO;
import no.noroff.moviecharactersapi.models.dtos.MovieDTOs.MoviePostDTO;
import no.noroff.moviecharactersapi.models.dtos.MovieDTOs.MoviePutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import no.noroff.moviecharactersapi.models.dtos.MovieDTOs.MovieDtoGetSimple;


@Mapper(componentModel = "spring")
public interface MovieMapper {

    Movie moviePostDtoToMovie(MoviePostDTO moviePostDto);
    Movie moviePutDtoToMovie(MoviePutDTO moviePutDto);

//    Movie movieDeleteDtoToMovie(MovieDeleteDTO movieDeleteDTO);

    @Mapping(target = "franchise", source = "franchise.id")
    @Mapping(target = "characters", qualifiedByName = "charactersToCharactersId")
    MovieDTO movieToMovieDto(Movie movie);

    Collection<MovieDTO> movieToMovieDto(Collection<Movie> movies); //for findAll

    @Named(value = "charactersToCharactersId")
    default Set<Integer> map(Set<Character> value){
        if(value == null)
            return null;
        return value.stream().map(c -> c.getId()).collect(Collectors.toSet());
    }

    MovieDtoGetSimple movieToMovieDtoSimple(Movie movie);


}
