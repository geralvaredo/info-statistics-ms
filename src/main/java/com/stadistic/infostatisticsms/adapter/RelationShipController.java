package com.stadistic.infostatisticsms.adapter;

import com.stadistic.infostatisticsms.dto.ConfirmRelationRequest;
import com.stadistic.infostatisticsms.dto.RelationshipDto;
import com.stadistic.infostatisticsms.entity.Relationship;
import com.stadistic.infostatisticsms.mapper.RelationMapper;
import com.stadistic.infostatisticsms.usecase.interfaz.ConfirmRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/relaciones")
public class RelationShipController {
    private final ConfirmRelation confirmRelation;
    private final RelationMapper relationMapper;

    @Autowired
    public RelationShipController(ConfirmRelation confirmRelation, RelationMapper relationMapper) {
        this.confirmRelation = confirmRelation;
        this.relationMapper = relationMapper;
    }

    @PostMapping()
    public RelationshipDto relationshipHandler(@RequestBody ConfirmRelationRequest confirmRelationRequest) {
        Relationship relationship = relationMapper.map(confirmRelationRequest);
        return relationMapper.map(confirmRelation.apply(relationship));
    }
}
