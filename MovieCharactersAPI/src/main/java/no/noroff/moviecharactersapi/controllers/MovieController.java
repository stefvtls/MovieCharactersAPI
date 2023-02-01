package no.noroff.moviecharactersapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.moviecharactersapi.mappers.MovieMapper;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.models.dto.movie.MovieDTO;
import no.noroff.moviecharactersapi.models.dto.movie.MovieSimpleDTO;
import no.noroff.moviecharactersapi.services.movie.MovieService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping // GET: localhost:8080/api/v1/movies
    @Operation(summary = "Gets all the movies in the database")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))
                    }
            )
    })
    public ResponseEntity getAll() {

        return ResponseEntity.ok(movieMapper.movieToMovieDto(movieService.findAll()));
    }

    @GetMapping("{id}") // GET: localhost:8080/api/v1/movies/1
    @Operation(summary = "Gets a movie by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MovieDTO.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)) //because you still got a weird msg
            )
    })
    public ResponseEntity getById(@PathVariable int id) {
        return ResponseEntity.ok(
                movieMapper.movieToMovieDto(
                        movieService.findById(id)
                ));
    }

    @PostMapping // POST: localhost:8080/api/v1/movies
    @Operation(summary = "Adds a new movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content
            )
    })
    public ResponseEntity add(@RequestBody Movie movie) {
        Movie mov = movieService.add(movie);
        URI location = URI.create("movies/" + mov.getId());
        return ResponseEntity.created(location).build();
    }

    //ACTUALLY CREATES NEW ENTRY WHEN NOT GIVING A ID IN BODY?
    //SHOULD NEVER HAPPEN WHEN GIVE A MOVIE OBJECT THOUGH (AS ID IS NOT NULL)
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/movies/1
    @Operation(summary = "Updates a movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Success",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content
            )
    }

    )
    public ResponseEntity update(@RequestBody Movie movie, @PathVariable int id) {
        // Validates if body is correct
//        if(id != movie.getId())
//            return ResponseEntity.badRequest().build();
        movieService.update(movie);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping // DELETE: localhost:8080/api/v1/movies
    public ResponseEntity delete(@RequestBody Movie movie) {
        movieService.deleteById(movie.getId());
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/movies/1
    public ResponseEntity deleteById(@PathVariable int id) {
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
