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
