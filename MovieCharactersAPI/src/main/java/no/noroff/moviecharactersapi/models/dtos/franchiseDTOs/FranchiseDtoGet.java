package no.noroff.moviecharactersapi.models.dtos.franchiseDTOs;

import jakarta.persistence.*;
import lombok.Data;
import no.noroff.moviecharactersapi.models.Movie;

import java.util.Set;

@Data
public class FranchiseDtoGet {
    private int id;
    private String name;
    private String description;
    private Set<Integer> movies;
}
