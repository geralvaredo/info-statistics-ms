package com.stadistic.infostatisticsms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDto {
    private String identifier;
    private Integer identifierType;
    private String name;
    private String lastName;
    private Integer country;
    private Timestamp birthDate;
    private Timestamp createdAt;
    private Timestamp deletedAt;
    private Timestamp updatedAt;
}
