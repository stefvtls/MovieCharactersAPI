package no.noroff.moviecharactersapi.repositories;

import no.noroff.moviecharactersapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository  extends JpaRepository<Movie,Integer> {
}

