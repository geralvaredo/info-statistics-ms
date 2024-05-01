package com.stadistic.infostatisticsms.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "RELATIONSHIP_TYPE")
public class RelationshipType {
    @Id
    @Column(name = "relationship_id")
    private Integer relationshipId;
    @Column(name = "relationship_description")
    private String relationshipDescription;
}
