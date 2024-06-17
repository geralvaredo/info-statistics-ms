package com.stadistic.infostatisticsms.adapter;

import com.stadistic.infostatisticsms.constants.Constants;
import com.stadistic.infostatisticsms.dto.ConfirmPersonRequest;
import com.stadistic.infostatisticsms.dto.PersonDto;
import com.stadistic.infostatisticsms.exception.PersonException;
import com.stadistic.infostatisticsms.mapper.PersonMapper;
import com.stadistic.infostatisticsms.usecase.interfaz.ConfirmPerson;
import com.stadistic.infostatisticsms.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/personas")
public class PersonController {

    private final ConfirmPerson confirmPerson;

    @Autowired
    public PersonController(ConfirmPerson confirmPerson) {
        this.confirmPerson = confirmPerson;
    }

    @PostMapping()
    public PersonDto savePersonHandler(@RequestBody ConfirmPersonRequest confirmPersonRequest) {
        if(!EmailUtil.isValidEmail(confirmPersonRequest.getEmail())) {
            throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_FORMAT_EMAIL_DESCRIPTION);
        } else {
            return confirmPerson.savePerson(confirmPersonRequest);
        }
    }

}
