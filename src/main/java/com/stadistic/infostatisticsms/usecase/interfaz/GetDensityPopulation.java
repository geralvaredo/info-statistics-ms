package com.stadistic.infostatisticsms.usecase.interfaz;

import com.stadistic.infostatisticsms.entity.DensityPopulation;

import java.util.List;

@FunctionalInterface
public interface GetDensityPopulation {
    List<DensityPopulation> apply();
}
