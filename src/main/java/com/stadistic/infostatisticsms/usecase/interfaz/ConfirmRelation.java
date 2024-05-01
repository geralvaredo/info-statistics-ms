package com.stadistic.infostatisticsms.usecase.interfaz;

import com.stadistic.infostatisticsms.dto.ConfirmRelationRequest;
import com.stadistic.infostatisticsms.dto.RelationshipDto;
import com.stadistic.infostatisticsms.entity.Relationship;

@FunctionalInterface
public interface ConfirmRelation {
    Relationship apply(Relationship relationship);
}
