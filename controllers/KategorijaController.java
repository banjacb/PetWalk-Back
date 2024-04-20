package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.NotFoundException;
import com.example.PetWalkback.models.dto.Kategorija;
import com.example.PetWalkback.models.requests.KategorijaRequest;
import com.example.PetWalkback.services.KategorijaService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kategorije")
public class KategorijaController {

    private final KategorijaService kategorijaService;

    public KategorijaController(KategorijaService kategorijaService) {
        this.kategorijaService = kategorijaService;
    }

    @GetMapping
    public List<Kategorija> findAll() { return kategorijaService.findAll(Kategorija.class);
    }

    @GetMapping("/{id}")
    public Kategorija findById(@PathVariable Integer id) throws NotFoundException {
        return kategorijaService.findById(id, Kategorija.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        kategorijaService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kategorija insert(@RequestBody @Valid KategorijaRequest kategorijaRequest) throws NotFoundException {
        return kategorijaService.insert(kategorijaRequest, Kategorija.class);
    }

    @PutMapping("/{id}")
    public Kategorija update(@PathVariable Integer id, @Valid @RequestBody KategorijaRequest kategorijaRequest) throws NotFoundException {
        return kategorijaService.update(id, kategorijaRequest, Kategorija.class);
    }

}
