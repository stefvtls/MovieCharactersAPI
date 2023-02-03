package no.noroff.moviecharactersapi.repositories;


import no.noroff.moviecharactersapi.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 Repository interface for the {@link Franchise} entity. Extends all the JPA Repository functionality
 */
@Repository
public interface FranchiseRepository extends JpaRepository<Franchise, Integer> {

}

