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
@Table(name = "IDENTIFIER_TYPE")
public class IdentifierType {
    @Id
    @Column(name = "identifier_type_id", insertable = false, updatable = false)
    private Integer identifierTypeId;
    @Column(name = "identifier_type_id")
    private String identifierTypeDescription;
}
