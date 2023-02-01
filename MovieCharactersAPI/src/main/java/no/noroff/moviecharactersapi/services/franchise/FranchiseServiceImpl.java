package no.noroff.moviecharactersapi.services.franchise;

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


    @Override
    public Franchise findById(Integer franchiseId) {
        return franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchiseNotFoundException(franchiseId));
    }

    @Override
    public Collection<Franchise> findAll() {
        return franchiseRepository.findAll();
    }


    @Override
    public Franchise add(Franchise entity) {
        return franchiseRepository.save(entity);
    }


    @Override
    public Franchise update(Franchise entity) {
        if (!franchiseRepository.existsById(entity.getId())){
            throw new FranchiseNotFoundException(entity.getId());
        }
        return franchiseRepository.save(entity);
    }


    @Override
    public void deleteById(Integer franchiseId) {
            Franchise franchise = franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchiseNotFoundException(franchiseId));
            franchise.getMovies().forEach(m -> m.setFranchise(null));
            franchise.getMovies().forEach(m -> movieRepository.save(m));
            franchiseRepository.delete(franchise);
    }

    @Override
    public void delete(Franchise entity) {
        deleteById(entity.getId());
    }


    @Override
    public Set<Movie> getAllMoviesInFranchise(int franchiseId) {
        return franchiseRepository.findById(franchiseId).orElseThrow(() -> new FranchiseNotFoundException(franchiseId)).getMovies();
    }

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
