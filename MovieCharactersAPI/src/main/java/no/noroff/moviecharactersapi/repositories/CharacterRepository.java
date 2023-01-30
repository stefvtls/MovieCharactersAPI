package no.noroff.moviecharactersapi.repositories;

import no.noroff.moviecharactersapi.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
}