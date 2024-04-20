package com.example.PetWalkback.models.requests;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class MapaRequest {
    @NotNull
    private Double koordinataX;
    @NotNull
    private Double koordinataY;
    @NotBlank
    private String nazivObjekta;
    @NotBlank
    private String opisObjekta;
    @NotBlank
    private String putanja;
}
