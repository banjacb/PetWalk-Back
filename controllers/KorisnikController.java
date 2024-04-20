package com.example.PetWalkback.controllers;

import com.example.PetWalkback.exceptions.ForbiddenException;
import com.example.PetWalkback.exceptions.NotFoundException;
import com.example.PetWalkback.models.dto.JwtUser;
import com.example.PetWalkback.models.dto.Korisnik;
import com.example.PetWalkback.models.dto.Ljubimac;
import com.example.PetWalkback.models.dto.Oglas;
import com.example.PetWalkback.models.entities.KorisnikEntity;
import com.example.PetWalkback.models.enums.Role;
import com.example.PetWalkback.models.requests.ChangeRoleRequest;
import com.example.PetWalkback.models.requests.ChangeStatusRequest;
import com.example.PetWalkback.models.requests.PasswordUpdateRequest;
import com.example.PetWalkback.models.requests.UserUpdateRequest;
import com.example.PetWalkback.services.KorisnikService;
import javax.validation.Valid;

import com.example.PetWalkback.services.LjubimacService;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/korisnici")
public class KorisnikController {
    private final KorisnikService korisnikService;
    private final LjubimacService ljubimacService;
    private final JdbcTemplate jdbcTemplate;

    public KorisnikController(KorisnikService korisnikService, LjubimacService ljubimacService, JdbcTemplate jdbcTemplate) {

        this.korisnikService = korisnikService;
        this.ljubimacService = ljubimacService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/{id}")
    public Korisnik findById(@PathVariable Integer id) throws NotFoundException {
        return korisnikService.findById(id, Korisnik.class);
    }

    @GetMapping
    public List<Korisnik> findAll() {
        List<Korisnik> korisnici = korisnikService.findAll(Korisnik.class);
        Collections.sort(korisnici, new Comparator<Korisnik>() {
            @Override
            public int compare(Korisnik korisnik1, Korisnik korisnik2) {
                return korisnik1.getLastName().compareTo(korisnik2.getLastName());
            }
        });
        return korisnici;

    }
    @PutMapping("/{id}")//novo
    public Korisnik update(@PathVariable Integer id, @Valid @RequestBody UserUpdateRequest request, Authentication auth) {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if (!jwtUser.getId().equals(id))
            throw new ForbiddenException();
        return korisnikService.update(id, request);
    }

    @PatchMapping("/{id}/status")//novo
    public void changeStatus(@PathVariable Integer id, @RequestBody @Valid ChangeStatusRequest request, Authentication auth) {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if (jwtUser.getId().equals(id))
            throw new ForbiddenException();
        korisnikService.changeStatus(id, request);
    }

    @PatchMapping("/{id}/role")//novo
    public void changeRole(@PathVariable Integer id, @RequestBody @Valid ChangeRoleRequest request, Authentication auth) {
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if(!jwtUser.getId().equals(id))
            throw new ForbiddenException();
        korisnikService.changeRole(id, request);
    }

    @PostMapping("/image")
    public void uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        korisnikService.saveImage(file.getBytes(), file.getOriginalFilename());
    }
    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]>  downloadImage(@PathVariable String imageName) throws IOException {
        return korisnikService.getImage(imageName);
    }

    @PutMapping("/{id}/{username}")
    public Korisnik updatePassword(@PathVariable Integer id, @Valid @RequestBody PasswordUpdateRequest request, @PathVariable String username, Authentication auth){
        JwtUser jwtUser = (JwtUser) auth.getPrincipal();
        if (!jwtUser.getId().equals(id))
            throw new ForbiddenException();
        return korisnikService.updatePassword(id, request, username);
    }

    @GetMapping("/filterByRole/{role}")
    public List<Korisnik> filterUsersByRole(@PathVariable Role role) {
        List<Korisnik> sviKorisnici = korisnikService.findAll(Korisnik.class);
        List<Korisnik> filtriraniKorisnici = new ArrayList<>();
        for(Korisnik k : sviKorisnici){
            if(k.getRole().equals(role))
                filtriraniKorisnici.add(k);
        }
        Collections.sort(filtriraniKorisnici, new Comparator<Korisnik>() {
            @Override
            public int compare(Korisnik korisnik1, Korisnik korisnik2) {
                return korisnik1.getLastName().compareTo(korisnik2.getLastName());
            }
        });
        return filtriraniKorisnici;
    }
}
