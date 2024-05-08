package com.stadistic.infostatisticsms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum IdentifierType {
    DNI(1, "DNI"),
    LIBRETA_CIVICA(2, "LIBRETA CIVICA"),
    LIBRETA_ENROLAMIENTO(3, "LIBRETA DE ENROLAMIENTO");

    private final Integer identifierTypeId;
    private final String identifierTypeDescription;

    public static IdentifierType getRelationshipDescription(String identifierTypeId) {
        return Stream.of(IdentifierType.values())
                .filter(x -> Objects.equals(x.getIdentifierTypeDescription(), identifierTypeId))
                .findFirst()
                .orElse(null);
    }
}
