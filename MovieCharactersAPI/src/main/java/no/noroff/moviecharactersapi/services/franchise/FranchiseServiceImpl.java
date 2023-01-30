package no.noroff.moviecharactersapi.services.franchise;

import no.noroff.moviecharactersapi.models.Franchise;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.repositories.FranchiseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class FranchiseServiceImpl implements FranchiseService{

    private final Logger logger = LoggerFactory.getLogger(FranchiseServiceImpl.class);
    private final FranchiseRepository franchiseRepository;


    public FranchiseServiceImpl(FranchiseRepository franchiseRepository) {
        this.franchiseRepository = franchiseRepository;
    }


    @Override
    public Franchise findById(Integer franchiseId) {
        return franchiseRepository.findById(franchiseId).get();
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
        return franchiseRepository.save(entity);
    }


    @Override
    public void deleteById(Integer franchiseId) {
        // find all movies where my franchise is

        //set it to null
        // delete franchise


    }

    @Override
    public void delete(Franchise entity) {

    }






    @Override
    public Set<Movie> getAllMoviesInFranchise(int franchiseId) {
        return franchiseRepository.findById(franchiseId).get().getMovies();
    }

    @Override
    public Set<Character> getAllCharactersInFranchise(int franchiseId) {
        Set<Character> charactersInFranchise = new HashSet<>();
        for (Movie m: franchiseRepository.findById(franchiseId).get().getMovies()) {
            for (Character character: m.getCharacters()) {
                charactersInFranchise.add(character);
            }
        }
        return charactersInFranchise;
    }

}
