package com.example.PetWalkback.models.requests;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class KategorijaRequest {

    @NotBlank
    private String kategorija;
    @NotNull
    private Integer oglasId;
}
