package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.NotFoundException;
import com.example.PetWalkback.models.dto.Korisnik;
import com.example.PetWalkback.models.dto.MonthlyTotal;
import com.example.PetWalkback.models.dto.Raspored;
import com.example.PetWalkback.models.requests.RasporedRequest;
import javax.validation.Valid;

import com.example.PetWalkback.services.KorisnikService;
import com.example.PetWalkback.services.LjubimacService;
import com.example.PetWalkback.services.RasporedService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rasporedi")
public class RasporedController {
    private final RasporedService rasporedService;
    private final LjubimacController ljubimacController;
    public RasporedController(RasporedService rasporedService,LjubimacController ljubimacController ) {
        this.rasporedService = rasporedService;
        this.ljubimacController=ljubimacController;
    }

    @GetMapping
    List<Raspored> findAll() {
        return rasporedService.findAll(Raspored.class);
    }

    @GetMapping("/{id}")
    public Raspored findById(@PathVariable Integer id) throws NotFoundException {
        return rasporedService.findById(id, Raspored.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        rasporedService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Raspored insert(@RequestBody RasporedRequest rasporedRequest) throws NotFoundException {
        return rasporedService.insert(rasporedRequest, Raspored.class);
    }

    @PutMapping("/{id}")
    public Raspored update(@PathVariable Integer id, @Valid @RequestBody RasporedRequest rasporedRequest) throws NotFoundException {
        return rasporedService.update(id, rasporedRequest, Raspored.class);
    }

    @GetMapping("/monthlyTotals/{id}")
    public List<MonthlyTotal> calculateMonthlyTotals(@PathVariable Integer id) {
        return rasporedService.calculateMonthlyTotals(id);
    }

    @GetMapping("/{vlasnikId}/{cuvarId}")
    public boolean provjeriVlasnika(@PathVariable Integer vlasnikId, @PathVariable Integer cuvarId) {

        List<Raspored> rasporedi = rasporedService.findAll(Raspored.class);

        for (Raspored raspored : rasporedi) {
            Korisnik vlasnik = ljubimacController.getKorisnik(raspored.getLjubimacId());
            if (vlasnik.getId().equals(vlasnikId) && raspored.getKorisnikId().equals(cuvarId))
                return true;
        }
        return false;
    }
}
