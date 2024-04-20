package com.example.PetWalkback.services.impl;

import com.example.PetWalkback.base.CrudJpaService;
import com.example.PetWalkback.models.dto.MonthlyTotal;
import com.example.PetWalkback.models.entities.RasporedEntity;
import com.example.PetWalkback.repositories.IzvjestajEntityRepository;
import javax.transaction.Transactional;

import com.example.PetWalkback.repositories.RasporedEntityRepository;
import com.example.PetWalkback.services.RasporedService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RasporedServiceImpl extends CrudJpaService<RasporedEntity, Integer> implements RasporedService {
    private final RasporedEntityRepository repository;
    public RasporedServiceImpl(ModelMapper modelMapper, RasporedEntityRepository repository) {
        super(repository, modelMapper, RasporedEntity.class);
        this.repository = repository;
    }

    @Override
    public <T, U> T insert(U object, Class<T> resultDtoClass) {
        return super.insert(object, resultDtoClass);
    }

    @Override
    public List<MonthlyTotal> calculateMonthlyTotals(Integer id) {
        List<Object[]> results = repository.calculateMonthlyTotals(id);

        List<MonthlyTotal> monthlyTotals = new ArrayList<>();
        for (Object[] result : results) {
            int month = (int) result[0];
            int year = (int) result[1];
            double totalPrice = (double) result[2];
            double totalTime = (double) result[3];

            MonthlyTotal monthlyTotal = new MonthlyTotal();
            monthlyTotal.setMonth(month);
            monthlyTotal.setYear(year);
            monthlyTotal.setTotalPrice(totalPrice);
            monthlyTotal.setTotalTime(totalTime);

            monthlyTotals.add(monthlyTotal);
        }

        return monthlyTotals;
    }
}
