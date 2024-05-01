package com.stadistic.infostatisticsms.repository;


import com.stadistic.infostatisticsms.entity.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

    Person findByIdentifier(Integer id);
    @Override
    Person save(Person person);
    boolean existsById (Integer id);
    List<Person> findByIdentifier(Integer ... id);

}
