package no.noroff.moviecharactersapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Franchise;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.services.franchise.FranchiseService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/franchises")
public class FranchiseController {

    private FranchiseService franchiseService;


    public FranchiseController(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
    }


    @GetMapping
    @Operation(summary = "get all franchises")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Franchise.class)))
                    }
            )
    })
    public ResponseEntity<Collection<Franchise>> getAll() {
        return ResponseEntity.ok(franchiseService.findAll());
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
                    description = "Bad request",
                    content = @Content
            )
    })
    public ResponseEntity add(@RequestBody Franchise franchise) {
        Franchise created = franchiseService.add(franchise);
        URI location = URI.create("franchises/" + created.getId());
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping
    @Operation(summary = "delete a franchise from the database")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content. Success",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content
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
    public ResponseEntity deleteEntity(@RequestBody Franchise franchise) {
        franchiseService.delete(franchise);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    @Operation(summary = "get franchise with a given id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ok. Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Franchise.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not Found",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class))
                    }
            )
    })
    public ResponseEntity<Franchise> getById(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.findById(id));
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
                    description = "Bad request",
                    content = @Content
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
    public ResponseEntity update(@PathVariable int id, @RequestBody Franchise franchise) {
        if (id != franchise.getId()) {
            return ResponseEntity.badRequest().build();     // going to throw 400 if id of new object and path to the object does not match
        }
        franchiseService.update(franchise);
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
                                            schema = @Schema(implementation = Movie.class)
                                    )
                            )
                    }
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
    public ResponseEntity<Set<Movie>> getAllMoviesForFranchise(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.getAllMoviesInFranchise(id));
    }


    @PutMapping("/{id}/movies")
    @Operation(summary = "assign a franchise with given id to every movie from given from the array of the movie IDs")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "No content. Success",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content
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
                                    array = @ArraySchema(schema = @Schema(implementation = Character.class)))
                    }
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
    public ResponseEntity<Set<Character>> getAllCharactersForFranchise(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.getAllCharactersInFranchise(id));
    }


}
