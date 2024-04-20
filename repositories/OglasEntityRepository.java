package com.example.PetWalkback.repositories;

import com.example.PetWalkback.models.dto.Oglas;
import com.example.PetWalkback.models.entities.OglasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OglasEntityRepository extends JpaRepository<OglasEntity, Integer> {
    List<OglasEntity> findAllByOrderByDatumAsc();

    List<OglasEntity> findAllByOrderByDatumDesc();
}
