package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.exceptions.ConflictException;
import com.example.PetWalkback.models.entities.KategorijaEntity;
import com.example.PetWalkback.models.entities.UslugaEntity;
import com.example.PetWalkback.repositories.KategorijaEntityRepository;
import com.example.PetWalkback.repositories.UslugaEntityRepository;
import javax.transaction.Transactional;

import com.example.PetWalkback.services.KategorijaService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class KategorijaServiceImpl extends CrudJpaService<KategorijaEntity, Integer> implements KategorijaService {
    private final KategorijaEntityRepository repository;
    public KategorijaServiceImpl(ModelMapper modelMapper, KategorijaEntityRepository repository){
        super(repository, modelMapper, KategorijaEntity.class);
        this.repository = repository;
    }

    @Override
    public <T, U> T insert(U object, Class<T> resultDtoClass) {
        return super.insert(object, resultDtoClass);
    }

    @Override
    public <T, U> T update(Integer integer, U object, Class<T> resultDtoClass) {
        if (!repository.existsById(integer))
            throw new ConflictException();
        return super.update(integer, object, resultDtoClass);
    }
}
