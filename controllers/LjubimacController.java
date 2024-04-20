package com.example.PetWalkback.controllers;

import com.example.PetWalkback.models.dto.Korisnik;
import com.example.PetWalkback.models.dto.Ljubimac;
import com.example.PetWalkback.models.dto.Vrsta;
import com.example.PetWalkback.models.requests.LjubimacRequest;
import com.example.PetWalkback.services.KorisnikService;
import com.example.PetWalkback.services.LjubimacService;
import javax.validation.Valid;
import com.example.PetWalkback.services.VrstaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.PetWalkback.exceptions.NotFoundException;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ljubimci")
public class LjubimacController {
    private final LjubimacService ljubimacService;
    private final VrstaService vrstaService;

    private final KorisnikService korisnikService;

    public LjubimacController(LjubimacService ljubimacService, VrstaService vrstaService, KorisnikService korisnikService) {
        this.ljubimacService = ljubimacService;
        this.vrstaService = vrstaService;
        this.korisnikService = korisnikService;
    }

    @GetMapping
    List<Ljubimac> findAll() {
        return ljubimacService.findAll(Ljubimac.class);
    }

    @GetMapping("/{id}")
    public Ljubimac findById(@PathVariable Integer id) throws NotFoundException {
        return ljubimacService.findById(id, Ljubimac.class);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        ljubimacService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ljubimac insert(@RequestBody LjubimacRequest ljubimacRequest) throws NotFoundException {
        return ljubimacService.insert(ljubimacRequest, Ljubimac.class);
    }

    @PutMapping("/{id}")
    public Ljubimac update(@PathVariable Integer id, @Valid @RequestBody LjubimacRequest ljubimacRequest) throws NotFoundException {
        return ljubimacService.update(id, ljubimacRequest, Ljubimac.class);
    }

    @PostMapping("/image")
    public void uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        ljubimacService.saveImage(file.getBytes(), file.getOriginalFilename());
    }
    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String imageName) throws IOException {
        return ljubimacService.getImage(imageName);
    }

    @GetMapping("vrsteLjubimaca/{naziv}")
    public List<Ljubimac> filterVrstaLjubimac(@PathVariable String naziv)
    {
        List<Ljubimac> sviLjubimci = ljubimacService.findAll(Ljubimac.class);
        List<Vrsta> sveVrste= vrstaService.findAll(Vrsta.class);
        List<Ljubimac> filtriraniLjubimci= new ArrayList<>();

        for(Vrsta vrsta:sveVrste)
        for(Ljubimac ljubimac: sviLjubimci)
            {
                if( vrsta.getId().equals(ljubimac.getVrstaId()) && vrsta.getNaziv().equals(naziv))
                    filtriraniLjubimci.add(ljubimac);
            }
        return  filtriraniLjubimci;
    }

    @GetMapping("getKorisnik/{id}")
    public Korisnik getKorisnik(@PathVariable Integer id){
        Ljubimac ljubimac = ljubimacService.findById(id, Ljubimac.class);
        return korisnikService.findById(ljubimac.getKorisnikId(), Korisnik.class);
    }

    @GetMapping("getLjubimca/{id}")
    public Integer getLjubimac(@PathVariable Integer id) {
        Korisnik korisnik = korisnikService.findById(id, Korisnik.class);
        List<Ljubimac> ljubimci = ljubimacService.findAll(Ljubimac.class);

        for (Ljubimac ljubimac : ljubimci) {
            if (korisnik.getId().equals(ljubimac.getKorisnikId())) {
                return ljubimac.getId();
            }
        }
        return null;
    }
}
