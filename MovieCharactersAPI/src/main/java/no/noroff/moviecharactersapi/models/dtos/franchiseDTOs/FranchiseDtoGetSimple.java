package no.noroff.moviecharactersapi.models.dtos.franchiseDTOs;

import lombok.Data;
import no.noroff.moviecharactersapi.models.Franchise;

/**
 * The Franchise DTO class which is used for simple displays in nested structures as reply to GET requests done by the
 * client when only the id is relevant.
 * @see Franchise
 */
@Data
public class FranchiseDtoGetSimple {
    private int id;
}
