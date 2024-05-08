package com.stadistic.infostatisticsms.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;


import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum RelationshipType {
    FATHER(1, "PADRE"),
    BROTHER(2, "HERMANO"),
    COUSIN(3, "PRIMO"),
    UNCLE(4, "TIO"),
    SON(5, "HIJO");

    private final Integer relationshipId;
    private final String relationshipDescription;

    public static RelationshipType getRelationship(String relationship) {
        return Stream.of(RelationshipType.values())
                .filter(x -> Objects.equals(x.getRelationshipDescription(), relationship)
                        || Objects.equals(x.getRelationshipId(), relationship))
                .findFirst()
                .orElse(null);
    }
}
