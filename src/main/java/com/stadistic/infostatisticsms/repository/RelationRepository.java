package com.stadistic.infostatisticsms.repository;

import com.stadistic.infostatisticsms.entity.Relationship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends CrudRepository<Relationship, Integer> {
    Relationship save(Relationship relationship);
}
