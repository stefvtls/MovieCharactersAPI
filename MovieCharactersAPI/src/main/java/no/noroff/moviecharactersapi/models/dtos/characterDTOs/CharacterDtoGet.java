package no.noroff.moviecharactersapi.models.dtos.characterDTOs;

import lombok.Data;


import java.util.Set;

@Data
public class CharacterDtoGet {

    private int id;

    private String name;

    private String alias;

    private String gender;

    private String picture;

    private Set<Integer> movies;
}
