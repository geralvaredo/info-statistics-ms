package com.stadistic.infostatisticsms.usecase.interfaz;

import com.stadistic.infostatisticsms.dto.ConfirmPersonRequest;
import com.stadistic.infostatisticsms.dto.PersonDto;
import com.stadistic.infostatisticsms.entity.Person;


public interface ConfirmPerson {
    PersonDto savePerson(ConfirmPersonRequest confirmPersonRequest);
}
