package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.models.entities.MapaEntity;
import com.example.PetWalkback.repositories.MapaEntityRepository;
import com.example.PetWalkback.services.MapaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class MapaServiceImpl extends CrudJpaService<MapaEntity, Integer> implements MapaService {
    private final MapaEntityRepository repository;
    public MapaServiceImpl(ModelMapper modelMapper, MapaEntityRepository repository) {
        super(repository, modelMapper, MapaEntity.class);
        this.repository = repository;
    }

    @Override
    public <T, U> T insert(U object, Class<T> resultDtoClass) {
        return super.insert(object, resultDtoClass);
    }
}
