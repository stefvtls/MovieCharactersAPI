package no.noroff.moviecharactersapi.mappers;

import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.models.dto.movie.MovieDTO;
import no.noroff.moviecharactersapi.models.dto.movie.MovieSimpleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieSimpleDTO movieToMovieSimpleDto(Movie movie);

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
}
