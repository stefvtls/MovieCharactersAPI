package no.noroff.moviecharactersapi.runner;

import no.noroff.moviecharactersapi.repositories.FranchiseRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;



@Component
public class Runner implements ApplicationRunner {
    final FranchiseRepository franchiseRepo;

    public Runner(FranchiseRepository franchiseRepo) {
        this.franchiseRepo = franchiseRepo;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {


        System.out.println(franchiseRepo.getAllCharacterIdsInFranchise(1));
    }
}
