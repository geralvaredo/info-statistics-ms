package com.stadistic.infostatisticsms.usecase.service;

import com.stadistic.infostatisticsms.dto.ConfirmPersonRequest;
import com.stadistic.infostatisticsms.dto.PersonDto;
import com.stadistic.infostatisticsms.entity.Person;
import com.stadistic.infostatisticsms.entity.Phone;
import com.stadistic.infostatisticsms.exception.PersonException;
import com.stadistic.infostatisticsms.mapper.PersonMapper;
import com.stadistic.infostatisticsms.repository.PersonRepository;
import com.stadistic.infostatisticsms.usecase.interfaz.ConfirmPerson;
import com.stadistic.infostatisticsms.utils.CurrentCredential;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.stadistic.infostatisticsms.constants.Constants.*;

@Service
public class ConfirmPersonImpl implements ConfirmPerson {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final CurrentCredential currentCredential;

    @Autowired
    public ConfirmPersonImpl(PersonRepository personRepository,
                             PersonMapper personMapper,
                             CurrentCredential currentCredential) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.currentCredential = currentCredential;
    }


    @Override
    @Transactional
    public PersonDto savePerson(ConfirmPersonRequest confirmPersonRequest) {
             if(!personRepository.existsByEmail(confirmPersonRequest.getEmail())) {
                 String personUid = UUID.randomUUID().toString();

                 List<Phone> phones = confirmPersonRequest.getPhones().stream()
                         .map(phoneRequest -> {
                             Phone phone = new Phone();
                             phone.setPhoneNumber(phoneRequest.getNumber());
                             phone.setCityCode(phoneRequest.getCityCode());
                             phone.setCountryCode(phoneRequest.getCountryCode());
                             phone.setPersonId(personUid);
                             return phone;
                         })
                         .collect(Collectors.toList());

                 Person person = Person.builder()
                         .name(confirmPersonRequest.getName())
                         .email(confirmPersonRequest.getEmail())
                         .password(confirmPersonRequest.getPassword())
                         .phones(phones)
                         .token(currentCredential.getAuthorization())
                         .lastLogin(Timestamp.valueOf(LocalDateTime.now()))
                         .created(Timestamp.valueOf(LocalDateTime.now()))
                         .active(Boolean.TRUE)
                         .build();

                 try{
                     personRepository.save(person);
                 } catch (Exception exception) {
                     throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                             ERROR_DESCRIPTION_SAVE_PERSON);
                 }

                 return  PersonDto.builder()
                         .id(person.getId())
                         .email(person.getEmail())
                         .token(person.getToken())
                         .lastLogin(person.getLastLogin())
                         .created(person.getCreated())
                         .modified(person.getModified())
                         .active(person.getActive())
                         .build();

             } else {
                 throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                         ERROR_DESCRIPTION_EXISTS_PERSON);
             }
    }

}
