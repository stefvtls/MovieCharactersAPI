package no.noroff.moviecharactersapi.models.dtos.MovieDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoviePutDTO {
    private int id;
    private String title;
    private String director;
    private String genre;
    private int releaseYear;
    private String moviePoster;
    private String trailer;
}
