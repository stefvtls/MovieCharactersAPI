package no.noroff.moviecharactersapi.models.dto.movie;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class MovieDTO {
    private int id;
    private String title;
    private String director;
    private int franchise;
    private Set<Integer> characters;

}
