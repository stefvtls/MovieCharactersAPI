package no.noroff.moviecharactersapi.runner;

import jakarta.transaction.Transactional;
import no.noroff.moviecharactersapi.repositories.FranchiseRepository;
import no.noroff.moviecharactersapi.models.Franchise;
import no.noroff.moviecharactersapi.services.franchise.FranchiseServiceImpl;
import no.noroff.moviecharactersapi.services.movie.MovieServiceImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class Runner implements ApplicationRunner {
    final FranchiseRepository franchiseRepo;
    private final FranchiseServiceImpl franchiseServiceImpl;
    private final MovieServiceImpl movieServiceImpl;

    public Runner(FranchiseRepository franchiseRepo, FranchiseServiceImpl franchiseServiceImpl, MovieServiceImpl movieServiceImpl) {
        this.franchiseRepo = franchiseRepo;
        this.franchiseServiceImpl = franchiseServiceImpl;
        this.movieServiceImpl = movieServiceImpl;
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {


//        System.out.println(franchiseRepo.getAllCharacterIdsInFranchise(1));
//        System.out.println(franchiseServiceImpl.getAllCharactersInFranchise(1));
//        franchiseServiceImpl.getAllCharactersInFranchise(1).forEach(s -> System.out.println(s.getName()));
//        System.out.println(franchiseServiceImpl.getAllMoviesInFranchise(1));
//        franchiseServiceImpl.getAllMoviesInFranchise(1).forEach(s-> System.out.println(s.getTitle()));
//        franchiseServiceImpl.deleteById(2);
//        Franchise fr = franchiseServiceImpl.findById(1);
//        franchiseServiceImpl.delete(fr);
//        franchiseServiceImpl.updateMoviesInFranchise(1, new int[]{4});


    }
}
