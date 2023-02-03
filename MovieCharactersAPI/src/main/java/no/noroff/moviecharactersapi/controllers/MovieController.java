package no.noroff.moviecharactersapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.moviecharactersapi.mappers.CharacterMapper;
import no.noroff.moviecharactersapi.mappers.MovieMapper;
import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.models.dtos.movieDTOs.MovieDtoGet;
import no.noroff.moviecharactersapi.models.dtos.movieDTOs.MovieDtoPost;
import no.noroff.moviecharactersapi.models.dtos.movieDTOs.MovieDtoPut;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoGetSimple;
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
    private final CharacterMapper characterMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper, CharacterMapper characterMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
    }


    /**
     * Handles GET requests at the URL: localhost:8080/api/v1/movies
     * Retrieves a collection of movies of type MovieDtoGet from the database.
     * @return a ResponseEntity with the HTTP status 200 (OK) if the retrieval is successful, or with a problem detail, HTTP status 400 (Bad Request).
     */
    @GetMapping // GET: localhost:8080/api/v1/movies
    @Operation(summary = "Gets all the movies in the database")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ok. Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MovieDtoGet.class)))
                    }
            ),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ProblemDetail.class)
                    ))

    })
    public ResponseEntity getAll() {

        return ResponseEntity.ok(movieMapper.movieToMovieDto(movieService.findAll()));
    }



    /**
     * Listens for GET requests at the URL: localhost:8080/api/v1/movies/{id}
     * Retrieves a movie from the database based on its id.
     * @param id of type int - the id of the movie to retrieve.
     * @return a ResponseEntity with the HTTP status 200 (OK) if the movie is successfully retrieved,
     * with the HTTP status 404 (Not Found) if the movie with the specified id is not found, or with the HTTP status 400 (Bad Request).
     */
    @GetMapping("{id}") // GET: localhost:8080/api/v1/movies/1
    @Operation(summary = "Gets a movie by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ok. Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MovieDtoGet.class))
                    }
            ),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ProblemDetail.class)
                    )),
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


    /**
     * Handles POST requests at the URL: localhost:8080/api/v1/movies
     * Adds a new movie to the database.
     * @param movieDtoPost of type MovieDtoPost - the movie to add.
     * @return a ResponseEntity with the HTTP status 201 (Created) if the movie is successfully added, or with a problem detail, HTTP status 400 (Bad Request).
     */
    @PostMapping // POST: localhost:8080/api/v1/movies
    @Operation(summary = "Adds a new movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content
            ),
            @ApiResponse(responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ProblemDetail.class)
                    ))
    })
    public ResponseEntity add(@RequestBody MovieDtoPost movieDtoPost) {
        Movie mov = movieService.add(movieMapper.moviePostDtoToMovie(movieDtoPost));
        URI location = URI.create("movies/" + mov.getId());
        return ResponseEntity.created(location).build();
    }



    /**
     * Listens for PUT requests at the URL: localhost:8080/api/v1/movies/{id}
     * Updates a movie with a given id.
     * @param id of type int - the id of the movie to update.
     * @param movieDtoPut of type MovieDtoPut - the movie to update.
     * @return a ResponseEntity with the HTTP status 204 (No Content) if the movie is successfully updated,
     * or with a problem detail, HTTP status 400 (Bad Request) if the id of the path does not match the id of the movie, or HTTP status 404 (Not Found).
     */
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/movies/1
    @Operation(summary = "Updates a movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content. Success",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ProblemDetail.class)
                    )
            )
    }
    )
    public ResponseEntity update(@RequestBody MovieDtoPut movieDtoPut, @PathVariable int id) {
        if(id != movieDtoPut.getId())
            return ResponseEntity.badRequest().build();
        movieService.update(movieMapper.moviePutDtoToMovie(movieDtoPut));
        return ResponseEntity.noContent().build();
    }



    /**
     * Listens for DELETE requests at the URL: localhost:8080/api/v1/movies/{id}
     * Deletes a movie with a given id.
     * @param id Id of the movie to delete
     * @return ResponseEntity with no content and a success status code of 204,
     * or a Bad Request status code of 400 and a response body with error details in
     * case of invalid request, or a Not Found status code of 404 and a response body with
     * error details in case the movie with the given id does not exist.
     */
    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/movies/1
    @Operation(summary = "Deletes a movie by ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content. Success",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    public ResponseEntity deleteById(@PathVariable int id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    /**
     Handles GET request at URL: localhost:8080/api/v1/movies/{id}/characters
     Gets all character for a movie with a given id.
     @param id Id of the movie
     @return ResponseEntity with a success status code of 200 and a response body
     containing an array of {@link CharacterDtoGetSimple} objects representing the characters,
     or a Bad Request status code of 400 and a response body with error details in
     case of invalid request, or a Not Found status code of 404 and a response body with
     error details in case the movie with the given id does not exist.
     */
    @GetMapping("{id}/characters") // GET: localhost:8080/api/v1/movies/1/characters
    @Operation(summary = "Gets all characters in a movie with given movie ID ")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Ok. Success",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CharacterDtoGetSimple.class)))
                    }),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ProblemDetail.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ProblemDetail.class)
                    )),
    })

    public ResponseEntity getCharacters(@PathVariable int id) {
        Collection<Character> chars = movieService.getCharacters(id);
        return ResponseEntity.ok(characterMapper.characterToCharacterDtoSimple(chars));

    }



    /**
     Handles PUT request at URL: localhost:8080/api/v1/movies/{id}/characters
     Assigns characters to a movie with given id.
     @param id Id of the movie
     @param characterIds Array of character IDs to assign to the movie
     @return ResponseEntity with no content and a success status code of 204,
     or a Bad Request status code of 400 and a response body with error details in
     case of invalid request, or a Not Found status code of 404 and a response body with
     error details in case the movie with the given id does not exist.
     */
    @PutMapping("{id}/characters") //PUT: localhost:8080/api/v1/movies/1/characters
    @Operation(summary = "Updates the characters in a movie with given movie ID by supplying a list of character IDs")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204",
                    description = "No content. Success",
                    content = @Content
            ),
            @ApiResponse(responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ProblemDetail.class)
                    )),
            @ApiResponse(responseCode = "404",
                    description = "Not Found",
                    content = @Content(
                            mediaType = "application/json",
                            schema=@Schema(implementation = ProblemDetail.class)
                    )),
    })

    public ResponseEntity updateCharacters(@PathVariable int id, @RequestBody int[] characterIds){
        movieService.updateCharacters(id, characterIds);
        return ResponseEntity.noContent().build();
    }

}
