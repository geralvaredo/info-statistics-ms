package com.stadistic.infostatisticsms.adapter;

import com.stadistic.infostatisticsms.entity.DensityPopulation;
import com.stadistic.infostatisticsms.usecase.interfaz.GetDensityPopulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class DensityPopulationController {

    private final GetDensityPopulation getDensityPopulation;

    @Autowired
    public DensityPopulationController(GetDensityPopulation getDensityPopulation) {
        this.getDensityPopulation = getDensityPopulation;
    }

    @GetMapping()
    public List<DensityPopulation> densityPopulationHandler() {
        return getDensityPopulation.apply();
    }

}
