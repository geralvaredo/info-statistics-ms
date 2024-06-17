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
    PersonDto map(Person person);

    PersonDto map(String token);
}
