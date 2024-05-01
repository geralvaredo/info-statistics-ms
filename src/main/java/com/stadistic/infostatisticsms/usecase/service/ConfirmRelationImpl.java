package com.stadistic.infostatisticsms.usecase.service;

import com.stadistic.infostatisticsms.entity.Relationship;
import com.stadistic.infostatisticsms.mapper.RelationMapper;
import com.stadistic.infostatisticsms.repository.RelationRepository;
import com.stadistic.infostatisticsms.usecase.interfaz.ConfirmRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmRelationImpl implements ConfirmRelation {

    private final RelationRepository relationRepository;

    @Autowired
    public ConfirmRelationImpl(RelationRepository relationRepository,
                               RelationMapper relationMapper) {
        this.relationRepository = relationRepository;
    }

    @Override
    public Relationship apply(Relationship relationship) {
        return relationRepository.save(relationship);
    }
}
