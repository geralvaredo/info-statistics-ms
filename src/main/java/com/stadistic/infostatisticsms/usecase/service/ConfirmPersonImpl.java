package com.stadistic.infostatisticsms.usecase.service;

import com.stadistic.infostatisticsms.entity.Person;
import com.stadistic.infostatisticsms.entity.Relationship;
import com.stadistic.infostatisticsms.entity.RelationshipType;
import com.stadistic.infostatisticsms.exception.PersonException;
import com.stadistic.infostatisticsms.factory.PersonFactory;
import com.stadistic.infostatisticsms.factory.RelationFactory;
import com.stadistic.infostatisticsms.repository.PersonRepository;
import com.stadistic.infostatisticsms.repository.RelationRepository;
import com.stadistic.infostatisticsms.usecase.interfaz.ConfirmPerson;
import com.stadistic.infostatisticsms.utils.StatisticsUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.stadistic.infostatisticsms.constants.Constants.*;
import static com.stadistic.infostatisticsms.entity.RelationshipType.getRelationship;

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
             Person person = personRepository.findByIdentifier(id);
             if(Objects.isNull(person)) {
                 throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                         ERROR_CODE_FIND_PERSON,
                         ERROR_DESCRIPTION_FIND_PERSON);
             }
             return person;
    }


    @Override
    @Transactional
    public String savePerson(Person person) {
             if(!personRepository.existsById(person.getIdentifier())) {
                 return savePersonIfsOverEighteen(person, SAVE, ERROR_CODE_SAVE_PERSON,
                         ERROR_DESCRIPTION_SAVE_PERSON);
             } else {
                 throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                         ERROR_CODE_EXISTS_PERSON,
                         ERROR_DESCRIPTION_EXISTS_PERSON);
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
            return savePersonIfsOverEighteen(person, UPDATE,
                    ERROR_CODE_UPDATE_PERSON,
                    ERROR_DESCRIPTION_UPDATE_PERSON);
        } else {
            throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ERROR_CODE_UPDATE_NOT_EXIST_PERSON,
                    ERROR_DESCRIPTION_NOT_EXIST_PERSON);
        }
    }

    @Override
    public String parentsRelation(Integer idFather, Integer idSon, String relation) {
        boolean fatherExists = personRepository.existsById(idFather);
        boolean sonExists = personRepository.existsById(idSon);
        if(fatherExists && sonExists) {
            return thereIsRelationShipWithFatherAndSon(idFather, idSon, relation);
        } else {
            throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ERROR_CODE_UPDATE_NOT_EXIST_PERSON,
                    ERROR_DESCRIPTION_NOT_EXIST_PERSON);
        }
    }

    private String thereIsRelationShipWithFatherAndSon(Integer idFather, Integer idSon, String relation) {
        if(Objects.isNull(relationRepository.findRelationByFatherAndSon(idFather,idSon))) {
            RelationshipType relationshipType = getRelationship(relation);
            Relationship relationship = Relationship.builder()
                    .identifierFirstRelative(idFather)
                    .identifierSecondRelative(idSon)
                    .relationshipType(relationshipType.getRelationshipId())
                    .build();
            return saveRelationShip(relationship, idFather, idSon);
        } else {
            throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ERROR_CODE_RELATION_EXISTS_PERSON,
                    ERROR_DESCRIPTION_RELATION_EXISTS_PERSON);
        }
    }

    private String saveRelationShip(Relationship relationship, Integer idFather, Integer idSon) {
        try {
            Relationship finalRelation = relationRepository.save(relationship);
            return relationFatherAndSon(finalRelation, idFather, idSon);
        } catch (Exception exception) {
            throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ERROR_CODE_SAVE_PERSON,
                    ERROR_DESCRIPTION_SAVE_RELATION_PERSON);
        }
    }

    private String relationFatherAndSon(Relationship relationship,
                                        Integer idFather,
                                        Integer idSon) {
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

        RelationshipType relationshipType = getRelationship(relationship.getRelationId().toString());

        return RelationFactory.relationFatherson(fatherFullName,
                relationshipType.getRelationshipDescription(),
                sonFullName);
    }

    private String savePersonIfsOverEighteen(Person person,
                                             String operation,
                                             String codeError,
                                             String codeDescription) {
        if(StatisticsUtils.isOverEighteen(person.getBirthDate())) {
            try{
                personRepository.save(person);
            } catch (Exception exception) {
                throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                        codeError,
                        codeDescription);
            }
            return PersonFactory.responsePersonDescription(person, operation);
        } else {
            throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR,
                    ERROR_CODE_SAVE_UNDER_EIGHTEEN_PERSON,
                    ERROR_DESCRIPTION_SAVE_UNDER_EIGHTEEN_PERSON);
        }
    }

}
