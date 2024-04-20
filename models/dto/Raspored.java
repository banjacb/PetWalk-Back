package com.example.PetWalkback.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Raspored {
    private Integer id;
    private Double ukupnaCijena;
    private Double vrijemeCuvanja;
    private Date datum;
    private Integer korisnikId;
    private Integer ljubimacId;

}
