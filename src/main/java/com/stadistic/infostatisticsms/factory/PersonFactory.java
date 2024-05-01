package com.stadistic.infostatisticsms.factory;

import com.stadistic.infostatisticsms.constants.Constants;
import com.stadistic.infostatisticsms.entity.Person;

public class PersonFactory {
    public static String responsePersonDescription(Person person, String status) {
        return Constants.PERSONA
                .concat(person.getName())
                .concat(" ")
                .concat(person.getLastName())
                .concat("fue")
                .concat(status);
    }
}
