package com.stadistic.infostatisticsms.adapter;

import com.stadistic.infostatisticsms.dto.ConfirmPersonRequest;
import com.stadistic.infostatisticsms.dto.PersonDto;
import com.stadistic.infostatisticsms.entity.Person;
import com.stadistic.infostatisticsms.mapper.PersonMapper;
import com.stadistic.infostatisticsms.usecase.interfaz.ConfirmPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/personas")
public class PersonController {

    private final ConfirmPerson confirmPerson;
    private final PersonMapper personMapper;

    @Autowired
    public PersonController(ConfirmPerson confirmPerson, PersonMapper personMapper) {
        this.confirmPerson = confirmPerson;
        this.personMapper = personMapper;
    }

    @GetMapping("/{id}")
    public PersonDto getPersonHandler(@RequestHeader Integer id) {
        return personMapper.map(confirmPerson.getPerson(id));
    }

    @PostMapping()
    public String savePersonHandler(@RequestBody ConfirmPersonRequest confirmPersonRequest) {
        Person person = personMapper.map(confirmPersonRequest);
        return confirmPerson.savePerson(person);

    }

    @PutMapping("/{id}")
    public String updatePersonHandler(@PathVariable Integer id,
                                      @RequestBody ConfirmPersonRequest confirmPersonRequest ) {
        Person person = personMapper.map(confirmPersonRequest);
        return confirmPerson.updatePerson(person, id);

    }

    @DeleteMapping("/{id}")
    public String deletePersonHandler(@PathVariable Integer id) {
        return confirmPerson.deletePerson(id);
    }

    @PostMapping("/{padre}/{relation}/{hijo}")
    public String relationshipHandler(@RequestHeader("padre") Integer son,
                                      @RequestHeader("hijo") Integer father,
                                      @RequestHeader("relation") String relationship) {
        return confirmPerson.parentsRelation(father, son, relationship);
    }

}
