//package no.noroff.moviecharactersapi;
//
//
//import jakarta.transaction.Transactional;
//import no.noroff.moviecharactersapi.services.character.CharacterService;
//import no.noroff.moviecharactersapi.services.movie.MovieService;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AppRunner implements ApplicationRunner {
//    private final MovieService movieService;
//    private final CharacterService characterService;
//
//    public AppRunner(MovieService movieService, CharacterService characterService) {
//        this.movieService = movieService;
//        this.characterService = characterService;
//    }
//
//    @Transactional
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        //movieService.findById(1).toString();
//        movieService.getCharacters(1).forEach(character -> System.out.println(character.getName()));
//        characterService.deleteById(1);
//        movieService.getCharacters(1).forEach(character -> System.out.println(character.getName()));
//        characterService.findAll().forEach(character -> System.out.println(character.getName()));
//    }
//}
