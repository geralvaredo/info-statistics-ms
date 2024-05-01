package com.stadistic.infostatisticsms.mapper;

import com.stadistic.infostatisticsms.dto.ConfirmPersonRequest;
import com.stadistic.infostatisticsms.dto.PersonDto;
import com.stadistic.infostatisticsms.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;

@Service
@Mapper(componentModel = "spring")
public interface PersonMapper {
    @Mappings({
            @Mapping(source = "identifierType", target = "identifierType.identifierTypeId"),
            @Mapping(source = "country", target = "country.countryId"),
            @Mapping(target = "deletedAt", ignore = true),
            @Mapping(target = "updatedAt", ignore = true)
    })
    Person map(ConfirmPersonRequest confirmPersonRequest);

    @Mappings({
            @Mapping(source = "identifierType.identifierTypeId", target = "identifierType"),
            @Mapping(source = "country.countryId", target = "country"),
    })
    PersonDto map(Person person);
}
