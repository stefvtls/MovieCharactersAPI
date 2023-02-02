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

    /**
     * Handles GET requests at the URL: localhost:8080/api/v1/franchises
     * Retrieves a collection of franchises of type FranchiseDtoGet from the database.
     * @return a ResponseEntity with the HTTP status 200 (OK) if the retrieval is successful, or with a problem detail, HTTP status 400 (Bad Request).
     */
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

    /**
     * Handles POST requests at the URL: localhost:8080/api/v1/franchises
     * Adds a new franchise to the database.
     * @param franchise of type FranchiseDtoPost - the franchise to add.
     * @return a ResponseEntity with the HTTP status 201 (Created) if the franchise is successfully added, or with a problem detail, HTTP status 400 (Bad Request).
     */
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

    /**
     * Listens for GET requests at the URL: localhost:8080/api/v1/franchises/{id}
     * Retrieves a franchise from the database based on its id.
     * @param id of type int - the id of the franchise to retrieve.
     * @return a ResponseEntity with the HTTP status 200 (OK) if the franchise is successfully retrieved,
     * with the HTTP status 404 (Not Found) if the franchise with the specified id is not found, or with the HTTP status 400 (Bad Request).
     */
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

    /**
     * Listens for PUT requests at the URL: localhost:8080/api/v1/franchises/{id}
     * Updates a franchise with a given id.
     * @param id of type int - the id of the franchise to update.
     * @param franchise of type FranchiseDtoPut - the franchise to update.
     * @return a ResponseEntity with the HTTP status 204 (No Content) if the franchise is successfully updated,
     * or with a problem detail, HTTP status 400 (Bad Request) if the id of the path does not match the id of the franchise, or HTTP status 404 (Not Found).
     */
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
            return ResponseEntity.badRequest().build();
        }
        franchiseService.update(franchiseMapper.franchiseDtoTofranchise(franchise));
        return ResponseEntity.noContent().build();
    }




    /**
     * Listens for DELETE requests at the URL: localhost:8080/api/v1/franchises/{id}
     * Deletes a franchise with a given id.
     * @param id Id of the franchise to delete
     * @return ResponseEntity with no content and a success status code of 204,
     * or a Bad Request status code of 400 and a response body with error details in
     * case of invalid request, or a Not Found status code of 404 and a response body with
     * error details in case the franchise with the given id does not exist.
     */
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



    /**
     Handles GET request at URL: localhost:8080/api/v1/franchises/{id}/movies
     Gets all movies for the franchise with a given id.
     @param id Id of the franchise
     @return ResponseEntity with a success status code of 200 and a response body
     containing an array of {@link MovieDtoGetSimple} objects representing the movies,
     or a Bad Request status code of 400 and a response body with error details in
     case of invalid request, or a Not Found status code of 404 and a response body with
     error details in case the franchise with the given id does not exist.
     */
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




    /**
     Handles PUT request at URL: localhost:8080/api/v1/franchises/{id}/movies
     Assigns movies to a franchise with given id.
     @param id Id of the franchise
     @param moviesIds Array of movie IDs to assign to the franchise
     @return ResponseEntity with no content and a success status code of 204,
     or a Bad Request status code of 400 and a response body with error details in
     case of invalid request, or a Not Found status code of 404 and a response body with
     error details in case the franchise with the given id does not exist.
     */
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

    /**
     Handles GET request at URL: localhost:8080/api/v1/franchises/{id}/characters
     Gets all characters which are to be found in the movies of a franchise with a given id.
     @param id Id of the franchise
     @return ResponseEntity with a success status code of 200 and a response body
     containing an array of {@link CharacterDtoGetSimple} objects representing the characters,
     or a Bad Request status code of 400 and a response body with error details in
     case of invalid request, or a Not Found status code of 404 and a response body with
     error details in case the franchise with the given id does not exist.
     */
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
