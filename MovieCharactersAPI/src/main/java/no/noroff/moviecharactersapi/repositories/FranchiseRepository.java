package no.noroff.moviecharactersapi.repositories;

import no.noroff.moviecharactersapi.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {


    @Query(value = "SELECT character.id FROM FRANCHISE\n" +
            "JOIN movie ON movie.franchise_id = franchise.id\n" +
            "JOIN movie_characters ON movie_characters.movies_id = movie.id\n" +
            "JOIN character ON movie_characters.characters_id = character.id\n" +
            "WHERE franchise.id = ?;", nativeQuery = true)
    Set<Integer> getAllCharacterIdsInFranchise(int franchiseId);


}

