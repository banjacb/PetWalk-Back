package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.NotFoundException;
import com.example.PetWalkback.models.dto.Korisnik;
import com.example.PetWalkback.models.dto.Recenzija;
import com.example.PetWalkback.models.requests.RecenzijaRequest;
import com.example.PetWalkback.services.KorisnikService;
import com.example.PetWalkback.services.RecenzijaService;
import javax.validation.Valid;

import org.modelmapper.internal.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/recenzije")
public class RecenzijaController {

    private final RecenzijaService recenzijaService;
    private final JdbcTemplate jdbcTemplate;

    private final KorisnikService korisnikService;

    public RecenzijaController(RecenzijaService recenzijaService, JdbcTemplate jdbcTemplate, KorisnikService korisnikService) {
        this.recenzijaService = recenzijaService;
        this.jdbcTemplate=jdbcTemplate;
        this.korisnikService = korisnikService;
    }

    @GetMapping
    List<Recenzija> findAll() {
        return recenzijaService.findAll(Recenzija.class);
    }

    @GetMapping("/{id}")
    public Recenzija findById(@PathVariable Integer id) throws NotFoundException {
        return recenzijaService.findById(id, Recenzija.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        recenzijaService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recenzija insert(@RequestBody RecenzijaRequest recenzijaRequest) throws NotFoundException {
        return recenzijaService.insert(recenzijaRequest, Recenzija.class);
    }

    @PutMapping("/{id}")
    public Recenzija update(@PathVariable Integer id, @Valid @RequestBody RecenzijaRequest recenzijaRequest) throws NotFoundException {
        return recenzijaService.update(id, recenzijaRequest, Recenzija.class);
    }

    @GetMapping("/prosjecnaOcjena")
    public List<Pair<Integer, BigDecimal>> izracunajProsjecneOcjene() {
        String query = "SELECT za_id, AVG(Ocjena) AS ProsjecnaOcjena FROM RECENZIJA GROUP BY za_id";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query);

        List<Pair<Integer, BigDecimal>> prosjecneOcjene = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            Integer korisnikId = (Integer) row.get("za_id");
            BigDecimal prosjecnaOcjena = (BigDecimal) row.get("ProsjecnaOcjena");
            prosjecneOcjene.add(Pair.of(korisnikId, prosjecnaOcjena));
        }

        return prosjecneOcjene;
    }

    @GetMapping("/sortByOcjena")
    public List<Korisnik> sortByOcjena(){
        List<Pair<Integer, BigDecimal>> prosjecneOcjene = izracunajProsjecneOcjene();
        Collections.sort(prosjecneOcjene, new Comparator<Pair<Integer, BigDecimal>>() {
            @Override
            public int compare(Pair<Integer, BigDecimal> pair1, Pair<Integer, BigDecimal> pair2) {
                return pair2.getRight().compareTo(pair1.getRight());
            }
        });
        List<Korisnik> korisnici = new ArrayList<>();
        for(Pair<Integer, BigDecimal> pair : prosjecneOcjene){
            Integer id = pair.getLeft();
            korisnici.add(korisnikService.findById(id, Korisnik.class));
        }

        return korisnici;
    }

}
