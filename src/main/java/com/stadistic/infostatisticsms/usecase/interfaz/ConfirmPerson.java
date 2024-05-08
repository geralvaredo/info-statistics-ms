package com.stadistic.infostatisticsms.usecase.interfaz;

import com.stadistic.infostatisticsms.entity.Person;


public interface ConfirmPerson {
    Person getPerson(Integer id);
    String savePerson(Person person);
    String deletePerson(Integer id);
    String updatePerson(Person person, Integer id);
    String parentsRelation(Integer idPadre, Integer idHijo, String relationship);

}
