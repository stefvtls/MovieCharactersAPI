package no.noroff.moviecharactersapi;


import jakarta.transaction.Transactional;
import no.noroff.moviecharactersapi.services.movie.MovieService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {
    private final MovieService movieService;

    public AppRunner(MovieService movieService) {
        this.movieService = movieService;
    }

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        //movieService.findById(1).toString();
        System.out.println(movieService.getCharacters(1));
    }
}
