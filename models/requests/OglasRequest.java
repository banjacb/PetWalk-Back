package com.example.PetWalkback.models.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OglasRequest {

    @NotBlank
    private String sadrzaj;
    @NotNull
    private boolean status;
    @NotNull
    private Integer kategorijaId;
    @NotNull
    private Date datum;
    @NotNull
    private Integer korisnikId;
}
