package com.stadistic.infostatisticsms.mapper;

import com.stadistic.infostatisticsms.dto.ConfirmRelationRequest;
import com.stadistic.infostatisticsms.dto.RelationshipDto;
import com.stadistic.infostatisticsms.entity.Relationship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface RelationMapper {

    @Mapping(source = "relationshipType", target = "relationshipType.relationshipId")
    Relationship map(ConfirmRelationRequest confirmRelationRequest);
    @Mapping(source = "relationshipType.relationshipId", target = "relationshipType")
    RelationshipDto map(Relationship relationship);
}
