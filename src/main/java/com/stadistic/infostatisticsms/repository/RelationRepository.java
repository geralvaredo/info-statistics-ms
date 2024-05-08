package com.stadistic.infostatisticsms.repository;

import com.stadistic.infostatisticsms.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<Relationship, Integer> {

    Relationship save(Relationship relationship);

    @Query(value = " SELECT relation_id FROM RELATIONSHIP r"
            + " WHERE identifier_first_relative = :father"
            + " AND identifier_second_relative = :son"
            + " OR identifier_first_relative = :son"
            + " AND identifier_second_relative = :father",nativeQuery = true)
    String findRelationByFatherAndSon(@Param("father") Integer father,
                                      @Param("son") Integer son);


}
