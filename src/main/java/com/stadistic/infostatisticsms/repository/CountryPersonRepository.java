package com.stadistic.infostatisticsms.repository;

import com.stadistic.infostatisticsms.entity.CountryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryPersonRepository extends JpaRepository<CountryPerson,Long> {
    @Query(value = " SELECT p.identifier as identifier , country_name as country"
            + " FROM PERSON p INNER JOIN COUNTRY c"
            + " WHERE p.country_id = c.country_id", nativeQuery = true)
    List<CountryPerson> getCountryPerson();
}
