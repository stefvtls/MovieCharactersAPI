package no.noroff.moviecharactersapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import no.noroff.moviecharactersapi.mappers.CharacterMapper;
import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoGet;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoPost;
import no.noroff.moviecharactersapi.models.dtos.characterDTOs.CharacterDtoPut;
import no.noroff.moviecharactersapi.services.character.CharacterService;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;


@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    public CharacterController(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    /**
     * Handles GET requests at the URL: localhost:8080/api/v1/characters
     * Retrieves a collection of characters of type CharacterDtoGet from the database.
     * @return a ResponseEntity with the HTTP status 200 (OK) if the retrieval is successful, or with a problem detail, HTTP status 400 (Bad Request).
     */
    @GetMapping // GET: localhost:8080/api/v1/characters
    @Operation(summary = "Get all characters")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ok. Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = CharacterDtoGet.class)
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
            )
    })
    public ResponseEntity<Collection<CharacterDtoGet>> getAll() {

        return ResponseEntity.ok(characterMapper.characterToCharacterDto(characterService.findAll()));
    }


    /**
     * Listens for GET requests at the URL: localhost:8080/api/v1/characters{id}
     * Retrieves a character from the database based on its id.
     * @param id of type int - the id of the character to retrieve.
     * @return a ResponseEntity with the HTTP status 200 (OK) if the character is successfully retrieved,
     * with the HTTP status 404 (Not Found) if the character with the specified id is not found, or with the HTTP status 400 (Bad Request).
     */
    @GetMapping("{id}") // GET: localhost:8080/api/v1/characters/1
    @Operation(summary = "Get a character with given id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ok. Success",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CharacterDtoGet.class)
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
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ProblemDetail.class)
                            )
                    }
            )
    })
    public ResponseEntity<CharacterDtoGet> getById(@PathVariable int id) {
        return ResponseEntity.ok(characterMapper.characterToCharacterDto(characterService.findById(id)));
    }


    /**
     * Handles POST requests at the URL: localhost:8080/api/v1/characters
     * Adds a new character to the database.
     * @param character of type CharacterDtoPost - the character to add.
     * @return a ResponseEntity with the HTTP status 201 (Created) if the character is successfully added, or with a problem detail, HTTP status 400 (Bad Request).
     */
    @PostMapping // POST: localhost:8080/api/v1/characters
    @Operation(summary = "Add a character to the database")
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
    public ResponseEntity add(@RequestBody CharacterDtoPost character) {
        Character charr = characterService.add(characterMapper.characterDtoPostToCharacter(character));
        URI location = URI.create("characters/" + charr.getId());
        return ResponseEntity.created(location).build();
    }


    /**
     Listens for PUT requests at the URL: localhost:8080/api/v1/characters/{id}
     Updates a character in the database based on its id.
     @param character of type CharacterDtoPut - the character to update.
     @param id of type int - the id of the character to update.
     @return a ResponseEntity with the HTTP status 204 (No Content) if the character is successfully updated,
     with the HTTP status 404 (Not Found) if the character with the specified id is not found, or with the HTTP status 400 (Bad Request).
     */
    @PutMapping("{id}") // PUT: localhost:8080/api/v1/characters/1
    @Operation(summary = "Update a character")
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
    public ResponseEntity update(@RequestBody CharacterDtoPut character, @PathVariable int id) {
        characterService.update(characterMapper.characterDtoPutToCharacter(character));
        return ResponseEntity.noContent().build();
    }


    /**
     Listens for DELETE requests at the URL: localhost:8080/api/v1/characters/{id}
     Deletes a character from the database based on its id.
     @param id of type int - the id of the character to delete.
     @return a ResponseEntity with the HTTP status 204 (No Content) if the character is successfully deleted,
     or with the HTTP status 404 (Not Found) or with the HTTP status 400 (Bad Request)..
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a character with a given id")
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
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
