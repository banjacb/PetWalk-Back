package com.example.PetWalkback.repositories;

import com.example.PetWalkback.models.dto.Korisnik;
import com.example.PetWalkback.models.entities.KorisnikEntity;
import com.example.PetWalkback.models.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KorisnikEntityRepository extends JpaRepository<KorisnikEntity, Integer> {
    Optional<KorisnikEntity> findByUsernameAndStatus(String username, KorisnikEntity.Status status);
    Boolean existsByUsername(String username);

    Boolean existsByUsernameAndIdNot(String username, Integer id);
    Boolean existsByIdNot(Integer id);

}
