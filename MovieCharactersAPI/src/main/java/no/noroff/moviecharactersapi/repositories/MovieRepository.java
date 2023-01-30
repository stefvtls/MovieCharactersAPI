package no.noroff.moviecharactersapi.repositories;

import no.noroff.moviecharactersapi.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository  extends JpaRepository<Movie,Integer> {


}

