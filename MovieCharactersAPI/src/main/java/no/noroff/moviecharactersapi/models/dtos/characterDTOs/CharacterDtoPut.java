package no.noroff.moviecharactersapi.models.dtos.characterDTOs;


import lombok.Data;

@Data
public class CharacterDtoPut {

    private int id;
    private String name;
    private String alias;
    private String gender;
    private String picture;
}
