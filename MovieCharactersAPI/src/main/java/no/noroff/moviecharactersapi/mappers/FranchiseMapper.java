package no.noroff.moviecharactersapi.mappers;

import no.noroff.moviecharactersapi.models.Franchise;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.models.dtos.franchiseDTOs.FranchiseDtoGet;
import no.noroff.moviecharactersapi.models.dtos.franchiseDTOs.FranchiseDtoGetSimple;
import no.noroff.moviecharactersapi.models.dtos.franchiseDTOs.FranchiseDtoPost;
import no.noroff.moviecharactersapi.models.dtos.franchiseDTOs.FranchiseDtoPut;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface FranchiseMapper {

    // GET DTO
    @Mapping(target="movies", qualifiedByName = "mapMoviesToIntegers")
    FranchiseDtoGet franchiseToFranchiseDto(Franchise franchise);

    @Mapping(target="movies", qualifiedByName = "mapMoviesToIntegers")
    Collection<FranchiseDtoGet> franchiseToFranchiseDto(Collection<Franchise> franchises);




    // POST DTO
    Franchise franchiseDtoTofranchise(FranchiseDtoPost franchiseDtoPost);

    // PUT DTO
    Franchise franchiseDtoTofranchise(FranchiseDtoPut franchiseDtoPut);





    @Named(value="mapMoviesToIntegers")
    default Set<Integer> mapMoviesToIntegers(Set<Movie> movies) {
        if (movies == null) {
            return null;
        }
        return movies.stream().map(Movie::getId).collect(Collectors.toSet());
    }


}
