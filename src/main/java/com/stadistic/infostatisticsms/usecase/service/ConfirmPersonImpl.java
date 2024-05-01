package com.stadistic.infostatisticsms.usecase.service;

import com.stadistic.infostatisticsms.entity.Person;
import com.stadistic.infostatisticsms.entity.Relationship;
import com.stadistic.infostatisticsms.exception.PersonException;
import com.stadistic.infostatisticsms.factory.PersonFactory;
import com.stadistic.infostatisticsms.factory.RelationFactory;
import com.stadistic.infostatisticsms.repository.PersonRepository;
import com.stadistic.infostatisticsms.repository.RelationRepository;
import com.stadistic.infostatisticsms.usecase.interfaz.ConfirmPerson;
import com.stadistic.infostatisticsms.utils.StatisticsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.stadistic.infostatisticsms.constants.Constants.*;

@Service
public class ConfirmPersonImpl implements ConfirmPerson {

    private final PersonRepository personRepository;
    private final RelationRepository relationRepository;

    @Autowired
    public ConfirmPersonImpl(PersonRepository personRepository,
                             RelationRepository relationRepository) {
        this.personRepository = personRepository;
        this.relationRepository = relationRepository;
    }

    @Override
    public Person getPerson(Integer id) {
        try {
            return personRepository.findByIdentifier(id);
        } catch (NullPointerException exception) {
            throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ERROR_CODE_FIND_PERSON,
                    ERROR_DESCRIPTION_FIND_PERSON);
        }
    }

    @Override
    public String savePerson(Person person) {
        try {
             if(personRepository.existsById(person.getIdentifier())) {
                 if(StatisticsUtils.isOverEighteen(person.getBirthDate())) {
                     personRepository.save(person);
                     return PersonFactory.responsePersonDescription(person, SAVE);
                 } else {
                     throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                             ERROR_CODE_SAVE_UNDER_EIGHTEEN_PERSON,
                             ERROR_DESCRIPTION_SAVE_UNDER_EIGHTEEN_PERSON);
                 }

             } else {
                 throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                         ERROR_CODE_EXISTS_PERSON,
                         ERROR_DESCRIPTION_EXISTS_PERSON);
             }
        } catch (Exception exception) {
            throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ERROR_CODE_SAVE_PERSON,
                    ERROR_DESCRIPTION_SAVE_PERSON);
        }
    }

    @Override
    public String deletePerson(Integer id) {
        if(personRepository.existsById(id)) {
            Person person = personRepository.findByIdentifier(id);
            person.setDeletedAt(Timestamp.valueOf(LocalDateTime.now()));
            try {
                personRepository.save(person);
                return PersonFactory.responsePersonDescription(person, DELETE);
            } catch (Exception exception) {
                throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                        ERROR_CODE_DELETE_PERSON,
                        ERROR_DESCRIPTION_DELETE_PERSON);
            }
        } else {
            throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ERROR_CODE_DELETE_NOT_EXIST_PERSON,
                    ERROR_DESCRIPTION_NOT_EXIST_PERSON);
        }
    }

    @Override
    public String updatePerson(Person person, Integer id) {
        if(Objects.nonNull(personRepository.findByIdentifier(id))) {
            person.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
            try {
                personRepository.save(person);
                return PersonFactory.responsePersonDescription(person, UPDATE);
            } catch (Exception exception) {
                throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                        ERROR_CODE_UPDATE_PERSON,
                        ERROR_DESCRIPTION_UPDATE_PERSON);
            }
        } else {
            throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ERROR_CODE_UPDATE_NOT_EXIST_PERSON,
                    ERROR_DESCRIPTION_NOT_EXIST_PERSON);
        }

    }

    @Override
    public String relation(Integer idFather, Integer idSon) {
        boolean fatherExists =  personRepository.existsById(idFather);
        boolean sonExists =  personRepository.existsById(idSon);

        if(fatherExists && sonExists) {
            Relationship relationship = Relationship.builder()
                    .identifierFirstRelative(idFather)
                    .identifierSecondRelative(idSon)
                    .build();
            try {
                Relationship relation = relationRepository.save(relationship);
                return relationFatherAndSon(relation, idFather, idSon);
            } catch (Exception exception) {
                throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                        ERROR_CODE_SAVE_PERSON,
                        ERROR_DESCRIPTION_SAVE_RELATION_PERSON);
            }
        } else {
            throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ERROR_CODE_UPDATE_NOT_EXIST_PERSON,
                    ERROR_DESCRIPTION_NOT_EXIST_PERSON);
        }
    }

    private String relationFatherAndSon(Relationship relationship, Integer idFather, Integer idSon) {
        List<Person> persons = personRepository.findByIdentifier(idFather, idSon);
        Person father = persons.stream()
                .filter(person -> person.getIdentifier().equals(idFather))
                .findFirst()
                .orElse(null);
        Person son = persons.stream()
                .filter(person -> person.getIdentifier().equals(idSon))
                .findFirst()
                .orElse(null);

        String fatherFullName = father.getName().concat("  ").concat(father.getLastName());
        String sonFullName = son.getName().concat("  ").concat(son.getLastName());

        return RelationFactory.relationFatherson(fatherFullName,
                relationship.getRelationshipType().getRelationshipDescription(),
                sonFullName);

    }

}
