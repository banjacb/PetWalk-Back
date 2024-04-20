package com.example.PetWalkback.models.dto;

import lombok.Data;

@Data
public class Mapa {
    private Integer id;
    private Double koordinataX;
    private Double koordinataY;
    private String nazivObjekta;
    private String opisObjekta;
    private String putanja;
}
