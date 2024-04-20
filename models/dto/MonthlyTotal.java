package com.example.PetWalkback.models.dto;

import lombok.Data;

@Data
public class MonthlyTotal {
    private int month;
    private int year;
    private double totalPrice;
    private double totalTime;
}
