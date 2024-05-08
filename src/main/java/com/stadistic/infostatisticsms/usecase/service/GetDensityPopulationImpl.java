package com.stadistic.infostatisticsms.usecase.service;

import com.stadistic.infostatisticsms.entity.CountryPerson;
import com.stadistic.infostatisticsms.entity.DensityPopulation;
import com.stadistic.infostatisticsms.repository.CountryPersonRepository;
import com.stadistic.infostatisticsms.usecase.interfaz.GetDensityPopulation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GetDensityPopulationImpl implements GetDensityPopulation {

    private final CountryPersonRepository countryPersonRepository;

    @Autowired
    public GetDensityPopulationImpl(CountryPersonRepository countryPersonRepository) {
        this.countryPersonRepository = countryPersonRepository;
    }

    @Override
    public List<DensityPopulation> apply() {
        List<CountryPerson> persons = countryPersonRepository.getCountryPerson();

        int maxPersons = persons.size();
        Map<String, Double> percentageByCountry = persons.stream()
                .collect(Collectors.groupingBy(CountryPerson::getCountry,
                        Collectors.collectingAndThen(Collectors.counting(),
                                count -> (double) count / maxPersons * 100)));

        List<DensityPopulation> densityPopulations = new ArrayList<>();

        percentageByCountry.forEach( (country, percentage) -> {
            DensityPopulation densityPopulation = DensityPopulation.builder()
                    .country(country)
                    .percentage(percentage)
                    .build();

            densityPopulations.add(densityPopulation);
        }  );

        return densityPopulations;
    }
}
