package no.noroff.moviecharactersapi.repositories;

import no.noroff.moviecharactersapi.models.Franchise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranchiseRepository extends JpaRepository<Franchise,Integer> {
}
