package com.example.gateway_service.model;
import java.time.LocalDateTime;

public class ImpactoAmbiental {
    private double totalKgCO2;
    private LocalDateTime dataCalculo;

    // getters e setters

    public double getTotalKgCO2() {
        return totalKgCO2;
    }

    public void setTotalKgCO2(double totalKgCO2) {
        this.totalKgCO2 = totalKgCO2;
    }

    public LocalDateTime getDataCalculo() {
        return dataCalculo;
    }

    public void setDataCalculo(LocalDateTime dataCalculo) {
        this.dataCalculo = dataCalculo;
    }
}