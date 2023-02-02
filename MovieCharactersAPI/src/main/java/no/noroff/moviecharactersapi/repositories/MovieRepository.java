package no.noroff.moviecharactersapi.repositories;

import no.noroff.moviecharactersapi.models.Character;
import no.noroff.moviecharactersapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 Repository interface for the {@link Movie} entity. Extends all the JPA Repository functionality
 */
@Repository
public interface MovieRepository  extends JpaRepository<Movie,Integer> {
}

