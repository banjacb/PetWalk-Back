package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.models.dto.Oglas;
import com.example.PetWalkback.models.entities.OglasEntity;
import com.example.PetWalkback.models.entities.RasporedEntity;
import com.example.PetWalkback.repositories.OglasEntityRepository;
import com.example.PetWalkback.services.OglasService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OglasServiceImpl extends CrudJpaService<OglasEntity, Integer> implements OglasService {
    private final OglasEntityRepository repository;
    private final ModelMapper modelMapper;
    public OglasServiceImpl(ModelMapper modelMapper, OglasEntityRepository repository) {
        super(repository, modelMapper, OglasEntity.class);
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public <T, U> T insert(U object, Class<T> resultDtoClass) {
        return super.insert(object, resultDtoClass);
    }

    @Override
    public List<Oglas> getAllOglasiSortedByDateAsc(){
        List<OglasEntity> oglasEntityList = repository.findAllByOrderByDatumAsc();
        return oglasEntityList.stream()
                .map(oglasEntity -> modelMapper.map(oglasEntity, Oglas.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Oglas> getAllOglasiSortedByDateDesc(){
        List<OglasEntity> oglasEntityList = repository.findAllByOrderByDatumDesc();
        return oglasEntityList.stream()
                .map(oglasEntity -> modelMapper.map(oglasEntity, Oglas.class))
                .collect(Collectors.toList());
    }
}
