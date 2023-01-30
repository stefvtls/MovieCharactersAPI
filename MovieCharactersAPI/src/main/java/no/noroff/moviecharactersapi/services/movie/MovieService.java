package no.noroff.moviecharactersapi.services.movie;



import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Franchise;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.services.CrudService;

import java.util.Collection;

public interface MovieService extends CrudService<Movie, Integer> {
    Franchise getFranchise(int movieId);
    Collection<Character> getCharacters(int movieId);
    void updateCharacters(int movieId, int[] characters);

}
