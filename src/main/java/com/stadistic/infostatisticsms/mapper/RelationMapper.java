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

    Relationship map(ConfirmRelationRequest confirmRelationRequest);
    RelationshipDto map(Relationship relationship);
}
