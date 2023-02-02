package no.noroff.moviecharactersapi.services.character;

import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Franchise;
import no.noroff.moviecharactersapi.services.CrudService;

/**
 * Service interface for the {@link Character} entity. Extends all the basic CRUD operations, and any new methods are to
 * be overridden in the {@link CharacterServiceImpl} class.
 */
public interface CharacterService extends CrudService<Character, Integer> {
}
