package com.example.PetWalkback.models.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class Problem {
    private Integer id;
    private String sadrzaj;
    private Integer korisnikId;
    private Date datum;
    private boolean status_problema;

}
