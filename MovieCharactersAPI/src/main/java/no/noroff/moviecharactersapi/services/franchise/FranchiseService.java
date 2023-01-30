package no.noroff.moviecharactersapi.services.franchise;

import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.services.CrudService;
import no.noroff.moviecharactersapi.models.Franchise;

import java.util.Set;

public interface FranchiseService extends CrudService<Franchise, Integer> {


    Set<Movie> getAllMoviesInFranchise(int franchiseId);
    Set<Character> getAllCharactersInFranchise(int franchiseId);

    void updateMoviesInFranchise(int franchiseId,  int[] moviesIds);

}
