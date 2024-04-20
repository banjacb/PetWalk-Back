package com.example.PetWalkback.services;

import com.example.PetWalkback.base.CrudService;
import com.example.PetWalkback.models.dto.MonthlyTotal;
import org.springframework.stereotype.Service;

import java.util.List;
public interface RasporedService extends CrudService<Integer> {
    List<MonthlyTotal> calculateMonthlyTotals(Integer id);
}
