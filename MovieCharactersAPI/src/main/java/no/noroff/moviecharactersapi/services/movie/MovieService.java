package no.noroff.moviecharactersapi.services.movie;



import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Franchise;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.services.CrudService;
import no.noroff.moviecharactersapi.services.franchise.FranchiseServiceImpl;

import java.util.Collection;

/**
 * Service interface for the {@link Movie} entity. Extends all the basic CRUD operations, and any new methods are to
 * be overridden in the {@link MovieServiceImpl} class. There are two extra methods, which deal with nested
 * structures for characters within the Movie table.
 */
public interface MovieService extends CrudService<Movie, Integer> {
//    Franchise getFranchise(int movieId);
    Collection<Character> getCharacters(int movieId);
    void updateCharacters(int movieId, int[] characters);

}
