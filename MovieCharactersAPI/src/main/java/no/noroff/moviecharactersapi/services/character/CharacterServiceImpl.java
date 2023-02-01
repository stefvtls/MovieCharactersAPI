package no.noroff.moviecharactersapi.services.character;

import no.noroff.moviecharactersapi.models.Character;

import no.noroff.moviecharactersapi.models.Franchise;
import no.noroff.moviecharactersapi.repositories.CharacterRepository;
import no.noroff.moviecharactersapi.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService{

    private final Logger logger = LoggerFactory.getLogger(CharacterServiceImpl.class);
    private final CharacterRepository characterRepository;
    private final MovieRepository movieRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository, MovieRepository movieRepository) {
        this.characterRepository = characterRepository;
        this.movieRepository = movieRepository;
    }

    @Override

    public Character findById(Integer characterId) {
        return characterRepository.findById(characterId).get();
    }


    @Override
    public Collection<Character> findAll() {
        return characterRepository.findAll();
    }

    @Override

    public Character add(Character entity) {
        return characterRepository.save(entity);
    }

    @Override
    public Character update(Character entity) {
        return characterRepository.save(entity);
    }

    @Override
    public void deleteById(Integer characterId) {
        if (characterRepository.existsById(characterId)) {
            Character character = characterRepository.findById(characterId).get();
            character.getMovies().forEach(m-> m.getCharacters().remove(character));
            character.getMovies().forEach(m-> movieRepository.save(m));
            characterRepository.delete(character);
        } else {
            logger.warn("No character exists with ID: " + characterId);
        }

    }

}
