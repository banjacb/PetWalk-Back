package com.example.PetWalkback.models.dto;


import lombok.Data;

import java.sql.Date;

@Data
public class Izvjestaj {
    private Integer id;
    private String sadrzaj;
    private Integer korisnikId;
    private Integer ljubimacId;
    private Date datum;
}
