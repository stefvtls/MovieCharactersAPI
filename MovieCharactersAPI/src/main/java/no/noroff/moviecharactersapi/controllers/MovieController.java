package no.noroff.moviecharactersapi.controllers;

import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.services.movie.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping // GET: localhost:8080/api/v1/movies
    public ResponseEntity<Collection<Movie>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("{id}") // GET: localhost:8080/api/v1/movies/1
    public ResponseEntity<Movie> getById(@PathVariable int id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping // POST: localhost:8080/api/v1/movies
    public ResponseEntity add(@RequestBody Movie movie) {
        Movie mov = movieService.add(movie);
        URI location = URI.create("movies/" + mov.getId());
        return ResponseEntity.created(location).build();
    }

    //ACTUALLY CREATES NEW ENTRY WHEN NOT GIVING A ID IN BODY?
    //SHOULD NEVER HAPPEN WHEN GIVE A MOVIE OBJECT THOUGH (AS ID IS NOT NULL)
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/movies/1
    public ResponseEntity update(@RequestBody Movie movie, @PathVariable int id) {
        // Validates if body is correct
//        if(id != movie.getId())
//            return ResponseEntity.badRequest().build();
        movieService.update(movie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/movies/1
    public ResponseEntity delete(@PathVariable int id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/characters") // GET: localhost:8080/api/v1/movies/1/characters
    public ResponseEntity getCharacters(@PathVariable int id) {
        return ResponseEntity.ok(movieService.getCharacters(id));

    }

    @PutMapping("{id}/characters") //PUT: localhost:8080/api/v1/movies/1/characters
    public ResponseEntity updateCharacters(@PathVariable int id, @RequestBody int[] characterIds){
        movieService.updateCharacters(id, characterIds);
        return ResponseEntity.noContent().build();
    }
}
