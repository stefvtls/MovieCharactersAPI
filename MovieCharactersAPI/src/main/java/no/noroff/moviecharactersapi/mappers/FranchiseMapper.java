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


/**
 The FranchiseMapper interface provides mapping functions for conversion between {@link Franchise} and its DTOs ({@link FranchiseDtoGet},
 {@link FranchiseDtoGetSimple}, {@link FranchiseDtoPost}, and {@link FranchiseDtoPut}).
 The mapping is done with MapStruct, a code generator for bean mapping.
 @see Franchise
 @see FranchiseDtoGet
 @see FranchiseDtoPost
 @see FranchiseDtoPut
 */
@Mapper(componentModel = "spring")
public interface FranchiseMapper {



    /**
     Maps a {@link Franchise} to a {@link FranchiseDtoGet}.
     The mapping also includes the conversion of the Set of {@link Movie} to a Set of movie IDs (integers) using the {@link #mapMoviesToIntegers} function.
     @param franchise the franchise to be mapped
     @return a franchise DTO
     */
    @Mapping(target="movies", qualifiedByName = "mapMoviesToIntegers")
    FranchiseDtoGet franchiseToFranchiseDto(Franchise franchise);




    /**
     Maps a Collection of {@link Franchise} to a Collection of {@link FranchiseDtoGet}.
     The mapping also includes the conversion of the Set of {@link Movie} to a Set of movie IDs (integers) using the {@link #mapMoviesToIntegers} function.
     @param franchises the collection of franchises to be mapped
     @return a collection of franchise DTOs
     */
    @Mapping(target="movies", qualifiedByName = "mapMoviesToIntegers")
    Collection<FranchiseDtoGet> franchiseToFranchiseDto(Collection<Franchise> franchises);



    /**
     Maps a {@link FranchiseDtoPost} to a {@link Franchise}.
     @param franchiseDtoPost the franchise DTO to be mapped
     @return a franchise
     */
    Franchise franchiseDtoTofranchise(FranchiseDtoPost franchiseDtoPost);




    /**
     Maps a {@link FranchiseDtoPut} to a {@link Franchise}.
     @param franchiseDtoPut the franchise DTO to be mapped
     @return a franchise
     */
    Franchise franchiseDtoTofranchise(FranchiseDtoPut franchiseDtoPut);



    /**
     Converts a Set of {@link Movie} to a Set of movie IDs (integers).
     @param movies the set of movies to be converted
     @return a set of movie IDs (integers)
     */
    @Named(value="mapMoviesToIntegers")
    default Set<Integer> mapMoviesToIntegers(Set<Movie> movies) {
        if (movies == null) {
            return null;
        }
        return movies.stream().map(Movie::getId).collect(Collectors.toSet());
    }


}
