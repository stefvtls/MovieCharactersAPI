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

    @GetMapping // GET: localhost:8080/api/v1/students
    public ResponseEntity<Collection<Movie>> getAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("{id}") // GET: localhost:8080/api/v1/students/1
    public ResponseEntity<Movie> getById(@PathVariable int id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping // POST: localhost:8080/api/v1/students
    public ResponseEntity add(@RequestBody Movie movie) {
        Movie mov = movieService.add(movie);
        URI location = URI.create("movies/" + mov.getId());
        return ResponseEntity.created(location).build();
        // return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
