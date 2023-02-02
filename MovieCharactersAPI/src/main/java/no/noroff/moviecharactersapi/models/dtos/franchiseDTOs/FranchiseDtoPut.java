package no.noroff.moviecharactersapi.models.dtos.franchiseDTOs;

import lombok.Data;
import no.noroff.moviecharactersapi.models.Franchise;

/**
 * The Franchise DTO class which is used for when a user want to update a franchise in the database. The user has to
 * provide the id for the franchise that needs to be updated, as well as all the fields in the franchise table (could be
 * left null)
 * @see Franchise
 */
@Data
public class FranchiseDtoPut {

    private int id;
    private String name;
    private String description;

}
