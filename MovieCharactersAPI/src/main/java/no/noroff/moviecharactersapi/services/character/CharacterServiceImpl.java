package no.noroff.moviecharactersapi.services.character;

import no.noroff.moviecharactersapi.exceptions.CharacterNotFoundException;
import no.noroff.moviecharactersapi.models.Character;

import no.noroff.moviecharactersapi.repositories.CharacterRepository;
import no.noroff.moviecharactersapi.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CharacterServiceImpl implements CharacterService {

    private final Logger logger = LoggerFactory.getLogger(CharacterServiceImpl.class);
    private final CharacterRepository characterRepository;
    private final MovieRepository movieRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository, MovieRepository movieRepository) {
        this.characterRepository = characterRepository;
        this.movieRepository = movieRepository;
    }

    /**
     * Gets a Character entity by its id.
     * @param characterId The id of the character to retrieve.
     * @return The character with the specified id.
     * @throws CharacterNotFoundException If character with id is not found.
     */
    @Override
    public Character findById(Integer characterId) {
        return characterRepository.findById(characterId).orElseThrow(() -> new CharacterNotFoundException(characterId));
    }

    /**
     * Retrieves a collection of all characters stored in the database.
     * @return A collection of all characters in the database.
     */
    @Override
    public Collection<Character> findAll() {
        return characterRepository.findAll();
    }

    /**
     * Adds a Character object entity to the database.
     * @param entity The character to be added
     * @return The saved Character entity
     */
    @Override
    public Character add(Character entity) {
        return characterRepository.save(entity);
    }

    /**
     * This method updates an existing Character entity in the database.
     * @param entity The Character object to be updated.
     * @return The updated Character object.
     * @throws CharacterNotFoundException if the character with the specified id does not exist in the database.
     */
    @Override
    public Character update(Character entity) {
        if (!characterRepository.existsById(entity.getId())){
            throw new CharacterNotFoundException(entity.getId());
        }
        return characterRepository.save(entity);
    }

    /**
     * This method deletes a Character entity from the database with the specified id. As characters are linked to
     * movies, this method also gets the movies where the character plays a part in, and updates their
     * character lists to one without the deleted character
     * @param characterId The id of the franchise to be deleted.
     * @throws CharacterNotFoundException if the franchise with the specified id does not exist in the database.
     */
    @Override
    public void deleteById(Integer characterId) {
        Character character = characterRepository.findById(characterId).orElseThrow(() -> new CharacterNotFoundException(characterId));
        character.getMovies().forEach(m -> m.getCharacters().remove(character));
        character.getMovies().forEach(m -> movieRepository.save(m));
        characterRepository.delete(character);
    }

}
