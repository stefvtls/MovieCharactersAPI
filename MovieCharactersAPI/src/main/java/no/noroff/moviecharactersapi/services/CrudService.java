package no.noroff.moviecharactersapi.services;

import no.noroff.moviecharactersapi.services.character.CharacterService;
import no.noroff.moviecharactersapi.services.franchise.FranchiseService;
import no.noroff.moviecharactersapi.services.movie.MovieService;

import java.util.Collection;

/**
 * Generic CRUD Service interface. Describes general behaviour that all entities in our database should be able to do
 * and are expended on in the entity specific interfaces
 * @see CharacterService
 * @see FranchiseService
 * @see MovieService
 */
public interface CrudService <T, ID> {
    // Generic CRUD
    T findById(ID id);
    Collection<T> findAll();
    T add(T entity);
    T update(T entity);
    void deleteById(ID id);
}
