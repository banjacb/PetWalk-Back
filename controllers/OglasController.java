package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.NotFoundException;

import com.example.PetWalkback.models.dto.Oglas;
import com.example.PetWalkback.models.requests.OglasRequest;
import com.example.PetWalkback.services.OglasService;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/oglasi")
public class OglasController {

    private final OglasService oglasService;
    private final JdbcTemplate jdbcTemplate;

    public OglasController(OglasService oglasService, JdbcTemplate jdbcTemplate) {
        this.oglasService = oglasService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    List<Oglas> findAll() {
        return oglasService.findAll(Oglas.class);
    }

    @GetMapping("/{id}")
    public Oglas findById(@PathVariable Integer id) throws NotFoundException {
        return oglasService.findById(id, Oglas.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        oglasService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Oglas insert(@RequestBody OglasRequest oglasRequest) throws NotFoundException {
        return oglasService.insert(oglasRequest, Oglas.class);
    }

    @PutMapping("/{id}")
    public Oglas update(@PathVariable Integer id, @Valid @RequestBody OglasRequest oglasRequest) throws NotFoundException {
        return oglasService.update(id, oglasRequest, Oglas.class);
    }


    @GetMapping("/kategorija/{kategorijaId}")
    public List<Oglas> filtrirajOglasePoKategoriji(@PathVariable int kategorijaId) {

        String query = "SELECT * FROM OGLAS WHERE kategorija_id = ?";

        List<Oglas> filtriraniOglasi = jdbcTemplate.query(query, new Object[]{kategorijaId}, (resultSet, rowNum) -> {
            Oglas oglas = new Oglas();
            oglas.setId(resultSet.getInt("id"));
            oglas.setSadrzaj(resultSet.getString("Sadrzaj"));
            oglas.setStatus(resultSet.getBoolean("Status"));
            oglas.setKategorijaId(resultSet.getInt("kategorija_id"));
            oglas.setDatum(resultSet.getDate("Datum"));
            oglas.setKorisnikId(resultSet.getInt("korisnik_id"));
            return oglas;
        });

        return filtriraniOglasi;
    }



    @GetMapping("/sortedByDateAsc")
    public List<Oglas> getAllOglasiSortedByDateAsc() {
        return oglasService.getAllOglasiSortedByDateAsc();
    }

    @GetMapping("/sortedByDateDesc")
    public List<Oglas> getAllOglasiSortedByDateDesc() {
        return oglasService.getAllOglasiSortedByDateDesc();
    }

}
