package no.noroff.moviecharactersapi.runner;

import jakarta.transaction.Transactional;
import no.noroff.moviecharactersapi.repositories.FranchiseRepository;
import no.noroff.moviecharactersapi.services.franchise.FranchiseService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;



@Component
public class Runner implements ApplicationRunner {
    final FranchiseRepository franchiseRepo;
    private final FranchiseService franchiseService;

    public Runner(FranchiseRepository franchiseRepo, FranchiseService franchiseService) {
        this.franchiseRepo = franchiseRepo;
        this.franchiseService = franchiseService;
    }


    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {


        System.out.println(franchiseRepo.getAllCharacterIdsInFranchise(1));
        System.out.println(franchiseService.getAllCharactersInFranchise(1));
        franchiseService.getAllCharactersInFranchise(1).forEach(s -> System.out.println(s.getName()));
        System.out.println(franchiseService.getAllMoviesInFranchise(1));
        franchiseService.getAllMoviesInFranchise(1).forEach(s-> System.out.println(s.getTitle()));
    }
}
