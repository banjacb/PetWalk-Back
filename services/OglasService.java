package com.example.PetWalkback.services;

import com.example.PetWalkback.base.CrudService;
import com.example.PetWalkback.models.dto.Oglas;

import java.util.List;

public interface OglasService extends CrudService<Integer> {
    List<Oglas> getAllOglasiSortedByDateAsc();
    List<Oglas> getAllOglasiSortedByDateDesc();
}
