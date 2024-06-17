package com.stadistic.infostatisticsms.repository;


import com.stadistic.infostatisticsms.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @Override
    Person save(Person person);
    boolean existsByEmail (String email);
}
