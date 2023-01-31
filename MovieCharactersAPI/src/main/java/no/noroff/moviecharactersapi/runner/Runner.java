package no.noroff.moviecharactersapi.runner;

import jakarta.transaction.Transactional;
import no.noroff.moviecharactersapi.services.character.CharacterServiceImpl;
import no.noroff.moviecharactersapi.services.franchise.FranchiseServiceImpl;
import no.noroff.moviecharactersapi.services.movie.MovieServiceImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class Runner implements ApplicationRunner {

    private final CharacterServiceImpl characterServiceImpl;
    private final FranchiseServiceImpl franchiseServiceImpl;
    private final MovieServiceImpl movieServiceImpl;


    public Runner(CharacterServiceImpl characterServiceImpl, FranchiseServiceImpl franchiseServiceImpl, MovieServiceImpl movieServiceImpl) {
        this.characterServiceImpl = characterServiceImpl;
        this.franchiseServiceImpl = franchiseServiceImpl;
        this.movieServiceImpl = movieServiceImpl;
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {



//        System.out.println(franchiseServiceImpl.getAllCharactersInFranchise(1));
//        franchiseServiceImpl.getAllCharactersInFranchise(1).forEach(s -> System.out.println(s.getName()));
//        System.out.println(franchiseServiceImpl.getAllMoviesInFranchise(1));
//        franchiseServiceImpl.getAllMoviesInFranchise(1).forEach(s-> System.out.println(s.getTitle()));
//        franchiseServiceImpl.deleteById(2);

//        characterServiceImpl.deleteById(1);
    }
}
