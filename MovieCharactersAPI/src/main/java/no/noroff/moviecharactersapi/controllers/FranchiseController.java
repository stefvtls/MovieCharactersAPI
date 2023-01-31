package no.noroff.moviecharactersapi.controllers;

import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Franchise;
import no.noroff.moviecharactersapi.models.Movie;
import no.noroff.moviecharactersapi.services.franchise.FranchiseService;
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
    public ResponseEntity<Collection<Franchise>> getAll() {
        return ResponseEntity.ok(franchiseService.findAll());
    }
    @PostMapping
    public ResponseEntity add(@RequestBody Franchise franchise) {
        Franchise created = franchiseService.add(franchise);
        URI location = URI.create("franchises/" + created.getId());
        return ResponseEntity.created(location).build();
    }




    @GetMapping("/{id}")
    public ResponseEntity<Franchise> getById (@PathVariable int id){
        return ResponseEntity.ok(franchiseService.findById(id));
    }

    @PutMapping("/{id}")
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
    public ResponseEntity updateMoviesInFranchise(@PathVariable int id, @RequestBody int[] moviesIds){
        franchiseService.updateMoviesInFranchise(id, moviesIds);
        return ResponseEntity.noContent().build();
    }




    @GetMapping("/{id}/characters")
    public ResponseEntity<Set<Character>> getAllCharacatersForFranchise(@PathVariable int id) {
        return ResponseEntity.ok(franchiseService.getAllCharactersInFranchise(id));
    }





}
