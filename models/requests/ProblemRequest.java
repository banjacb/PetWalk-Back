package com.example.PetWalkback.models.requests;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class ProblemRequest {
    @NotNull
    private Integer id;
    @NotBlank
    private String sadrzaj;
    @NotNull
    private Integer korisnikId;
    @NotNull
    private Date datum;
    @NotNull
    private boolean status_problema;
}
