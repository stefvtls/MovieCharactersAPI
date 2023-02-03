package no.noroff.moviecharactersapi.services.movie;


import no.noroff.moviecharactersapi.exceptions.CharacterNotFoundException;
import no.noroff.moviecharactersapi.exceptions.MovieNotFoundException;
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

//     /**
//     * This method retrieves the Franchise entity for a movie with the specified id.
//     * @param movieId The id of the movie for which the Franchise is to be retrieved.
//     * @return The Franchise object associated with the movie.
//     * @throws MovieNotFoundException if the movie with the specified id does not exist in the database.
//      */
//    @Override
//    public Franchise getFranchise(int movieId) {
//        return movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId)).getFranchise();
//    }

    /**
     * Returns the collection of characters in a movie specified by the movieId.
     * @param movieId the id of the movie for which the characters are being retrieved.
     * @return a collection of characters in the specified movie.
     * @throws MovieNotFoundException if the movie with the specified movieId is not found.
     */
    @Override
    public Collection<Character> getCharacters(int movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId)).getCharacters();
    }

    /**
     * Updates the list of characters associated with a specified movie. This method retrieves the movie with the
     * specified id, then finds the characters corresponding to the character ids passed in the `characters` parameter.
     * The updated list of characters is then set on the movie and saved to the movie table.
     *
     * @param movieId the id of the movie to be updated
     * @param characters an array of character ids to associate with the movie
     * @throws MovieNotFoundException if the movie with the specified id cannot be found in the movie repository
     * @throws CharacterNotFoundException if any of the specified character ids do not correspond to a known character
     * in the character repository
     */
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

    /**
     * Gets a movie by its id.
     * @param id The id of the movie to retrieve.
     * @return The movie with the specified id.
     * @throws MovieNotFoundException If movie with id is not found.
     */
    @Override
    public Movie findById(Integer id) {

        return movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    /**
     * Retrieves a collection of all movies stored in the database.
     * @return A collection of all movies in the database.
     */
    @Override
    public Collection<Movie> findAll() {
        return movieRepository.findAll();
    }

    /**
     * Adds a Movie object entity to the database.
     * @param entity The Movie to be added
     * @return The saved Movie entity
     */
    @Override
    public Movie add(Movie entity) {
        return movieRepository.save(entity);
    }

    /**
     * This method updates an existing movie entity in the database.
     * @param entity The Movie object to be updated.
     * @return The updated Movie object.
     * @throws MovieNotFoundException if the movie with the specified id does not exist in the database.
     */
    @Override
    public Movie update(Movie entity) {
        if (!movieRepository.existsById(entity.getId())){
            throw new MovieNotFoundException(entity.getId());
        }
        return movieRepository.save(entity);
    }

    /**
     * This method deletes a movie from the database with the specified id.
     * @param movieId The id of the movie to be deleted.
     * @throws MovieNotFoundException if the movie with the specified id does not exist in the database.
     */
    @Override
    public void deleteById(Integer movieId) {
            Movie mov = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
            movieRepository.delete(mov);
    }

}
