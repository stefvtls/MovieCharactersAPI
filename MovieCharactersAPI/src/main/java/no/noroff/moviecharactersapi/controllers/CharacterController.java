package no.noroff.moviecharactersapi.controllers;

import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.services.character.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;


@RestController
@RequestMapping(path = "api/v1/characters")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping // GET: localhost:8080/api/v1/characters
    public ResponseEntity<Collection<Character>> getAll() {
        return ResponseEntity.ok(characterService.findAll());
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

    @DeleteMapping // DELETE: localhost:8080/api/v1/characters
    public ResponseEntity delete(@RequestBody Character character) {
        characterService.deleteById(character.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}") // DELETE: localhost:8080/api/v1/characters/1
    public ResponseEntity deleteById(@PathVariable int id) {
        characterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
