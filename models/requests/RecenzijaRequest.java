package com.example.PetWalkback.models.requests;


import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class RecenzijaRequest {

    @Nullable
    private String komentar;
    @NotNull
    private Integer ocjena;
    @NotNull
    private Integer korisnikOdId;
    @NotNull
    private Integer korisnikZaId;
    @Nullable
    private Date datum;
}
