package com.example.PetWalkback.models.requests;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class VrstaRequest {

    @NotBlank
    private String naziv;
}
