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
    @Operation(summary = "get all characters")
    @ApiResponses(value={
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
    public ResponseEntity<Character> getById(@PathVariable int id) {
        return ResponseEntity.ok(characterService.findById(id));
    }

    @PostMapping // POST: localhost:8080/api/v1/characters
    public ResponseEntity add(@RequestBody Character character) {
        Character charr = characterService.add(character);
        URI location = URI.create("characters/" + charr.getId());
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}") // PUT: localhost:8080/api/v1/characters/1
    public ResponseEntity update(@RequestBody Character character, @PathVariable int id) {
        characterService.update(character);
        return ResponseEntity.noContent().build();
    }

//    @DeleteMapping // DELETE: localhost:8080/api/v1/characters
//    public ResponseEntity delete(@RequestBody Character character) {
//        characterService.deleteById(character.getId());
//        return ResponseEntity.noContent().build();
//    }

    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/characters/1
    public ResponseEntity deleteById(@PathVariable int id) {
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
