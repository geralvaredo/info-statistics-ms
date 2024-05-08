package com.stadistic.infostatisticsms.repository;


import com.stadistic.infostatisticsms.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByIdentifier(Integer id);
    @Override
    Person save(Person person);
    boolean existsById (Integer id);
    List<Person> findByIdentifier(Integer ... id);

}
