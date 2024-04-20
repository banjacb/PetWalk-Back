package com.example.PetWalkback.models.dto;


import lombok.Data;

import java.util.Date;

@Data
public class Oglas {
    private Integer id;
    private String sadrzaj;
    private Date datum;
    private Integer kategorijaId;
    private boolean status;
    private Integer korisnikId;
}
