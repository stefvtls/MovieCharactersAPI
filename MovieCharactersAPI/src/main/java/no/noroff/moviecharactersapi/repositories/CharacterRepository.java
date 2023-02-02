package no.noroff.moviecharactersapi.repositories;

import no.noroff.moviecharactersapi.models.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 Repository interface for the {@link Character} entity. Extends all the JPA Repository functionality
 */
@Repository
public interface CharacterRepository extends JpaRepository<Character, Integer> {
}