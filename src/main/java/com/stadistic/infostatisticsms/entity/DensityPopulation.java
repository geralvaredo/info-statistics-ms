package com.stadistic.infostatisticsms.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DensityPopulation {
    private String country;
    private Double percentage;
}
