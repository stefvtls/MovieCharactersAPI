package no.noroff.moviecharactersapi.models.dtos.franchiseDTOs;

import jakarta.persistence.*;
import lombok.Data;
import no.noroff.moviecharactersapi.models.Franchise;

import java.util.Set;


/**
 * The Franchise DTO class which is used to display all the fields in the Franchise table as reply to GET
 * requests done by the client.
 * @see Franchise
 */

@Data
public class FranchiseDtoGet {
    private int id;
    private String name;
    private String description;
    private Set<Integer> movies;
}
