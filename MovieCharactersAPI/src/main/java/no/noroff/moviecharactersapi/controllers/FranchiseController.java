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
                    description = "Success",
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
                    content = @Content)
    })
    public ResponseEntity add(@RequestBody Franchise franchise) {
        Franchise created = franchiseService.add(franchise);
        URI location = URI.create("franchises/" + created.getId());
        return ResponseEntity.created(location).build();
    }


    @GetMapping("/{id}")
    @Operation(summary = "get franchise with a given id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Success",
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
                    description = "Success",
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
                    content = @Content
            )}
    )
    public ResponseEntity update(@PathVariable int id, @RequestBody Franchise franchise) {
        franchiseService.update(franchise);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable int id) {
        franchiseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/movies")
    public ResponseEntity<Set<Movie>> getAllMoviesForFranchise(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.getAllMoviesInFranchise(id));
    }

    @PutMapping("/{id}/movies")
    public ResponseEntity updateMoviesInFranchise(@PathVariable int id, @RequestBody int[] moviesIds) {
        franchiseService.updateMoviesInFranchise(id, moviesIds);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}/characters")
    public ResponseEntity<Set<Character>> getAllCharacatersForFranchise(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.getAllCharactersInFranchise(id));
    }


}
