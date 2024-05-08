package com.stadistic.infostatisticsms.entity;

import jakarta.persistence.*;

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
@Table(name = "RELATIONSHIP")
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_id")
    private Integer relationId;
    @Column(name = "identifier_first_relative")
    private Integer identifierFirstRelative;
    @Column(name = "identifier_second_relative")
    private Integer identifierSecondRelative;
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "relationship_id")
    private Integer relationshipType;
}
