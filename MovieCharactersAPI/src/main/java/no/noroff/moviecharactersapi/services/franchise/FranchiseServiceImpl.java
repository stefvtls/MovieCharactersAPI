package no.noroff.moviecharactersapi.services.franchise;

import no.noroff.moviecharactersapi.exceptions.CharacterNotFoundException;
import no.noroff.moviecharactersapi.exceptions.FranchiseNotFoundException;
import no.noroff.moviecharactersapi.exceptions.MovieNotFoundException;
import no.noroff.moviecharactersapi.models.Franchise;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.repositories.FranchiseRepository;
import no.noroff.moviecharactersapi.repositories.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class FranchiseServiceImpl implements FranchiseService {

    private final Logger logger = LoggerFactory.getLogger(FranchiseServiceImpl.class);
    private final FranchiseRepository franchiseRepository;
    private final MovieRepository movieRepository;

    public FranchiseServiceImpl(FranchiseRepository franchiseRepository, MovieRepository movieRepository) {
        this.franchiseRepository = franchiseRepository;
        this.movieRepository = movieRepository;
    }

    /**
     * This method retrieves a Franchise entity with the specified id from the database.
     * @param franchiseId The id of the franchise to be retrieved.
     * @return The Franchise object with the specified id.
     * @throws FranchiseNotFoundException if the franchise with the specified id does not exist in the database.
     *
     * */
    @Override
    public Franchise findById(Integer franchiseId) {
        return franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchiseNotFoundException(franchiseId));
    }

    /**
     * Retrieves all Franchise entities from the database.
     * @return A collection of all Franchise objects in the database.
     * */
    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }

    /**
     * Adds a new franchise to the database.
     * @param entity The Franchise object to be added.
     * @return The added Franchise object, with any generated fields (such as id) set.
     */
    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }

    /**
     * Updates an existing Franchise entity in the database.
     * @param entity The Franchise object to be updated. The object should have the id field set, representing
     * the id of the franchise to be updated in the database.
     * @return The updated Franchise object. The returned object will have the updated fields, including any
     * changes made to the entity that was passed as an argument.
     * @throws FranchiseNotFoundException if the franchise with the specified id does not exist in the database. This
     * exception is thrown when the method is unable to find a franchise in the database with the same id as the id
     * field of the entity parameter.
     */
    @Override
    public Franchise update(Franchise entity) {
        if (!franchiseRepository.existsById(entity.getId())){
            throw new FranchiseNotFoundException(entity.getId());
        }
        return franchiseRepository.save(entity);
    }

    /**
     * This method deletes a Franchise entity from the database with the specified id. As franchises are linked to
     * movies, this method also gets the movies that belong to the franchise that is being deleted, and updates their
     * franchise id to null.
     * @param franchiseId The id of the franchise to be deleted.
     * @throws FranchiseNotFoundException if the franchise with the specified id does not exist in the database.
     */
    @Override
    public void deleteById(Integer franchiseId) {
            Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchiseNotFoundException(franchiseId));
            franchise.getMovies().forEach(m -> m.setFranchise(null));
            franchise.getMovies().forEach(m -> movieRepository.save(m));
            franchiseRepository.delete(franchise);
    }

    /**
     * Returns the collection of movies belonging to a franchise specified by the franchiseId.
     * @param franchiseId the id of the franchise for which the movies are being retrieved.
     * @return a collection of movies belonging to the specific franchise
     * @throws FranchiseNotFoundException if the franchise with the specified franchiseId is not found.
     */
    @Override
    public Set<Movie> getAllMoviesInFranchise(int franchiseId) {
        return franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchiseNotFoundException(franchiseId)).getMovies();
    }

    /**
     * Returns the collection of characters belonging to a franchise specified by the franchiseId. This is achieved by
     * first retrieving the movies that belong to a franchise, after which the characters that are a part of these
     * movies are retrieved and added to the collection
     * @param franchiseId the id of the franchise for which the characters are being retrieved.
     * @return a collection of characters which play a role in the movies belonging to the specific franchise
     * @throws FranchiseNotFoundException if the franchise with the specified franchiseId is not found.
     */
    @Override
    public Set<Character> getAllCharactersInFranchise(int franchiseId) {
        Set<Character> charactersInFranchise = new HashSet<>();
        for (Movie m : franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchiseNotFoundException(franchiseId)).getMovies()) {
            for (Character character : m.getCharacters()) {
                charactersInFranchise.add(character);
            }
        }
        return charactersInFranchise;
    }


    /**
     * Updates the list of movies belonging to a specified franchise. This method retrieves this franchise with the
     * specified id, then finds all movies corresponding to the movie ids passed in the `movieIds` parameter.
     * The updated list of movies is then set on the franchise and saved to the franchise table.
     *
     * @param franchiseId the id of the franchise to be updated
     * @param moviesIds an array of movie ids to associate with the franchise
     * @throws FranchiseNotFoundException if the franchise with the specified id cannot be found in the franchise repository
     * @throws MovieNotFoundException if any of the specified movie ids do not correspond to a known movie
     * in the character repository
     */
    @Override
    public void updateMoviesInFranchise(int franchiseId, int[] moviesIds) {
        Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchiseNotFoundException(franchiseId));
        for (int movieId : moviesIds) {
            Movie movieToUpdate = movieRepository.findById(movieId).orElseThrow(() -> new MovieNotFoundException(movieId));
            movieToUpdate.setFranchise(franchise);
            movieRepository.save(movieToUpdate);
        }

    }

}
