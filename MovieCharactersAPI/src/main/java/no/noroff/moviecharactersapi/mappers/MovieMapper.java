package no.noroff.moviecharactersapi.mappers;


import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.models.dtos.MovieDTOs.MovieDtoGetSimple;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDtoGetSimple movieToMovieDtoSimple(Movie movie);

}
