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
import no.noroff.moviecharactersapi.models.dtos.MovieDTOs.MovieDTO;
import no.noroff.moviecharactersapi.models.dtos.MovieDTOs.MovieDtoGetSimple;
import no.noroff.moviecharactersapi.models.dtos.MovieDTOs.MoviePostDTO;
import no.noroff.moviecharactersapi.models.dtos.MovieDTOs.MoviePutDTO;
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

    @GetMapping // GET: localhost:8080/api/v1/movies
    @Operation(summary = "Gets all the movies in the database")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ok. Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))
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

    @GetMapping("{id}") // GET: localhost:8080/api/v1/movies/1
    @Operation(summary = "Gets a movie by its ID")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ok. Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = MovieDTO.class))
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
    public ResponseEntity add(@RequestBody MoviePostDTO moviePostDto) {
        Movie mov = movieService.add(movieMapper.moviePostDtoToMovie(moviePostDto));
        URI location = URI.create("movies/" + mov.getId());
        return ResponseEntity.created(location).build();
    }

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
    public ResponseEntity update(@RequestBody MoviePutDTO moviePutDto, @PathVariable int id) {
        if(id != moviePutDto.getId())
            return ResponseEntity.badRequest().build();
        movieService.update(movieMapper.moviePutDtoToMovie(moviePutDto));
        return ResponseEntity.noContent().build();
    }

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
        return ResponseEntity.ok(characterMapper.characterToCharacterSimpleDto(chars));

    }

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

//    @DeleteMapping // DELETE: localhost:8080/api/v1/movies
//    @Operation(summary = "Deletes a movie (i guess not needed?)")
//    @ApiResponses(value = {
//            @ApiResponse(
//                    responseCode = "204",
//                    description = "Success",
//                    content = @Content
//            ),
//            @ApiResponse(
//                    responseCode = "400",
//                    description = "Bad Request",
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema=@Schema(implementation = ProblemDetail.class)
//                    )
//            ),
//            @ApiResponse(
//                    responseCode = "404",
//                    description = "Not Found",
//                    content = @Content(
//                            mediaType = "application/json",
//                            schema=@Schema(implementation = ProblemDetail.class)
//                    )
//            )
//    })
//    public ResponseEntity delete(@RequestBody MovieDeleteDTO movieDeleteDTO) {
//        Movie mov = movieMapper.movieDeleteDtoToMovie(movieDeleteDTO);
//        movieService.deleteById(mov.getId());
//        return ResponseEntity.noContent().build();
//    }
}
