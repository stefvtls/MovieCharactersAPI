package no.noroff.moviecharactersapi.models.dto.movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSimpleDTO {
    private int id;
    private String title;
//    private String director; //because we made it a requirement, so bad choice i guess
}
