package com.stadistic.infostatisticsms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Optional;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfirmPersonRequest {
    private Integer identifier;
    private Integer identifierType;
    private String name;
    private String lastName;
    private Integer country;
    private Timestamp birthDate;
    private Timestamp createdAt;
    private final Optional<Timestamp> deletedAt = Optional.empty();
    private final Optional<Timestamp> updatedAt = Optional.empty();

}
