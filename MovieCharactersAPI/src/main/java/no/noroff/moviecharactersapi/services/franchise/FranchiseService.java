package no.noroff.moviecharactersapi.services.franchise;

import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.services.CrudService;
import no.noroff.moviecharactersapi.models.Franchise;


import java.util.Set;

/**
 * Service interface for the {@link Franchise} entity. Extends all the basic CRUD operations, and any new methods are to
 * be overridden in the {@link FranchiseServiceImpl} class. There are three extra methods, which deal with nested
 * structures within the Franchise table.
 */
public interface FranchiseService extends CrudService<Franchise, Integer> {


    Set<Movie> getAllMoviesInFranchise(int franchiseId);
    Set<Character> getAllCharactersInFranchise(int franchiseId);

    void updateMoviesInFranchise(int franchiseId,  int[] moviesIds);

}
