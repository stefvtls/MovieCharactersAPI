package no.noroff.moviecharactersapi.services.movie;

import jakarta.transaction.Transactional;
import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.repositories.CharacterRepository;
import no.noroff.moviecharactersapi.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class MovieServiceImpl implements MovieService {
    private final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);
    private final MovieRepository movieRepository;
    private final CharacterRepository characterRepository;

    public MovieServiceImpl(MovieRepository movieRepository, CharacterRepository characterRepository) {
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
    }


    @Override
    public Collection<Character> getCharacters(int movieId) {
        return movieRepository.findById(movieId).get().getCharacters();
    }

    @Override
    public void updateCharacters(int movieId, int[] characters) {
        Movie movie = movieRepository.findById(movieId).get();
        Set<Character> characterList = new HashSet<>();

        for (int id: characters) {
            characterList.add(characterRepository.findById(id).get());
        }
        movie.setCharacters(characterList);
        movieRepository.save(movie);
    }

    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id).orElseThrow();
    }


    @Override
    public Collection<Movie> findAll() {
        return null;
    }

    @Override
    public Movie add(Movie entity) {
        return null;
    }

    @Override
    public Movie update(Movie entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Movie entity) {

    }
}
