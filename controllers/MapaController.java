package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.NotFoundException;
import com.example.PetWalkback.models.dto.Mapa;
import com.example.PetWalkback.models.requests.MapaRequest;
import com.example.PetWalkback.services.MapaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/mape")
public class MapaController {
    private final MapaService mapaService;

    public MapaController(MapaService mapaService) {
        this.mapaService = mapaService;
    }


    @GetMapping
    List<Mapa> findAll() {
        return mapaService.findAll(Mapa.class);
    }

    @GetMapping("/{id}")
    public Mapa findById(@PathVariable Integer id) throws NotFoundException {
        return mapaService.findById(id, Mapa.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        mapaService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mapa insert(@RequestBody MapaRequest mapaRequest) throws NotFoundException {
        return mapaService.insert(mapaRequest, Mapa.class);
    }

    @PutMapping("/{id}")
    public Mapa update(@PathVariable Integer id, @Valid @RequestBody MapaRequest mapaRequest) throws NotFoundException {
        return mapaService.update(id, mapaRequest, Mapa.class);
    }
}
