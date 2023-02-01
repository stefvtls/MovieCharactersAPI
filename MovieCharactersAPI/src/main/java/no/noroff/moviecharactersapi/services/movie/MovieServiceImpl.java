package no.noroff.moviecharactersapi.services.movie;

import jakarta.transaction.Transactional;
import no.noroff.moviecharactersapi.exceptions.CharacterNotFoundException;
import no.noroff.moviecharactersapi.exceptions.FranchiseNotFoundException;
import no.noroff.moviecharactersapi.exceptions.MovieNotFoundException;
import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Franchise;
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
    public Franchise getFranchise(int movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId)).getFranchise();
    }

    @Override
    public Collection<Character> getCharacters(int movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId)).getCharacters();
    }

    @Override
    public void updateCharacters(int movieId, int[] characters) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
        Set<Character> characterList = new HashSet<>();

        for (int id: characters) {
            characterList.add(characterRepository.findById(id).orElseThrow(() -> new CharacterNotFoundException(id)));
        }
        movie.setCharacters(characterList);
        movieRepository.save(movie);
    }


    @Override
    public Movie findById(Integer id) {

        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }


    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie add(Movie entity) {
        return movieRepository.save(entity);
    }

    @Override
    public Movie update(Movie entity) {
        if (!movieRepository.existsById(entity.getId())){
            throw new MovieNotFoundException(entity.getId());
        }
        return movieRepository.save(entity);
    }

    @Override
    public void deleteById(Integer movieId) {
            Movie mov = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
            movieRepository.delete(mov);
    }

//
//    @Override
//    public void delete(Movie entity) {
//        int movieId = entity.getId();
//        deleteById(movieId);
//    }
}
