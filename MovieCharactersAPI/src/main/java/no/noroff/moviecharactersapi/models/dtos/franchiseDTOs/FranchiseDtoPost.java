package no.noroff.moviecharactersapi.models.dtos.franchiseDTOs;


import lombok.Data;
import no.noroff.moviecharactersapi.models.Franchise;

/**
 * The Franchise DTO class which is used for when a user want to add a new franchise to the database. Only the name is
 * required as ids are auto-generated. Other fields for a franchise have to be updated with PUT requests.
 * @see Franchise
 * @see FranchiseDtoPut
 **/
@Data
public class FranchiseDtoPost {

    private String name;

}

