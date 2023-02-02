package no.noroff.moviecharactersapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.moviecharactersapi.mappers.CharacterMapper;
import no.noroff.moviecharactersapi.mappers.FranchiseMapper;
import no.noroff.moviecharactersapi.mappers.MovieMapper;
import no.noroff.moviecharactersapi.models.Franchise;
import no.noroff.moviecharactersapi.models.dtos.movieDTOs.MovieDtoGetSimple;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoGetSimple;
import no.noroff.moviecharactersapi.models.dtos.franchiseDTOs.FranchiseDtoGet;
import no.noroff.moviecharactersapi.models.dtos.franchiseDTOs.FranchiseDtoPost;
import no.noroff.moviecharactersapi.models.dtos.franchiseDTOs.FranchiseDtoPut;
import no.noroff.moviecharactersapi.services.franchise.FranchiseService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api/v1/franchises")
public class FranchiseController {

    private FranchiseService franchiseService;
    private FranchiseMapper franchiseMapper;
    private MovieMapper movieMapper;
    private CharacterMapper characterMapper;


    public FranchiseController(FranchiseService franchiseService, FranchiseMapper franchiseMapper, MovieMapper movieMapper, CharacterMapper characterMapper) {
        this.franchiseService = franchiseService;
        this.franchiseMapper = franchiseMapper;
        this.movieMapper = movieMapper;
        this.characterMapper = characterMapper;
    }


    @GetMapping
    @Operation(summary = "get all franchises")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = FranchiseDtoGet.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    public ResponseEntity<Collection<FranchiseDtoGet>> getAll() {
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDto(franchiseService.findAll()));
    }


    @PostMapping
    @Operation(summary = "add a new franchise to the database")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    public ResponseEntity add(@RequestBody FranchiseDtoPost franchise) {
        Franchise created = franchiseService.add(franchiseMapper.franchiseDtoTofranchise(franchise));
        URI location = URI.create("franchises/" + created.getId());
        return ResponseEntity.created(location).build();
    }


    @GetMapping("/{id}")
    @Operation(summary = "get franchise with a given id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ok. Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = FranchiseDtoGet.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    public ResponseEntity<FranchiseDtoGet> getById(@PathVariable int id) {
        return ResponseEntity.ok(franchiseMapper.franchiseToFranchiseDto(franchiseService.findById(id)));
    }


    @PutMapping("/{id}")
    @Operation(summary = "update a franchise")
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
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    }
            )}
    )
    public ResponseEntity update(@PathVariable int id, @RequestBody FranchiseDtoPut franchise) {
        if (id != franchise.getId()) {
            return ResponseEntity.badRequest().build();     // going to throw 400 if id of new object and path to the object does not match
        }
        franchiseService.update(franchiseMapper.franchiseDtoTofranchise(franchise));
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "delete a franchise with a given id")
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
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    }
            )
    })
    public ResponseEntity deleteById(@PathVariable int id) {
        franchiseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/movies")
    @Operation(summary = "get all movies for the franchise with a given id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ok. Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = MovieDtoGetSimple.class)
                                    )
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    public ResponseEntity getAllMoviesForFranchise(@PathVariable int id) {
        return ResponseEntity.ok(movieMapper.movieToMovieDtoSimple(franchiseService.getAllMoviesInFranchise(id)));
    }


    @PutMapping("/{id}/movies")
    @Operation(summary = "assign movies to a franchise - assign a franchise with given id to every movie from given from the array of the movie IDs")
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
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    public ResponseEntity updateMoviesInFranchise(@PathVariable int id, @RequestBody int[] moviesIds) {
        franchiseService.updateMoviesInFranchise(id, moviesIds);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/characters")
    @Operation(summary = "Get all characters which are to be found in the movies of a franchise with a given id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = CharacterDtoGetSimple.class)))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    }
    )
    public ResponseEntity getAllCharactersForFranchise(@PathVariable int id) {
        return ResponseEntity.ok(characterMapper.characterToCharacterDtoSimple(franchiseService.getAllCharactersInFranchise(id)));
    }


}
