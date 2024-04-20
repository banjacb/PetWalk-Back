package com.example.PetWalkback.models.requests;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RasporedRequest {

    @NotNull
    private Double vrijemeCuvanja;
    @NotNull
    private Double ukupnaCijena;
    @NotNull
    private Date datum;
    @NotNull
    private Integer korisnikId;
    @NotNull
    private Integer ljubimacId;

}
