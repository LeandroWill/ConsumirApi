package com.example.consumirapi3.response;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

//A anotação abaixo é feita para que os valores nao retornem nulos.
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CharacterResponse {
    private String  id;
    private String name;
    private String status;
    private String species;
    private String image;
    private List<String> episode;
}
