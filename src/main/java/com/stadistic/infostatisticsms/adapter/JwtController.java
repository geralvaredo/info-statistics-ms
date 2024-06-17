package com.stadistic.infostatisticsms.adapter;

import com.stadistic.infostatisticsms.constants.Constants;
import com.stadistic.infostatisticsms.dto.AuthenticationRequest;
import com.stadistic.infostatisticsms.dto.PersonDto;
import com.stadistic.infostatisticsms.exception.PersonException;
import com.stadistic.infostatisticsms.mapper.PersonMapper;
import com.stadistic.infostatisticsms.usecase.interfaz.GetToken;
import com.stadistic.infostatisticsms.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class JwtController {
    private final GetToken getToken;
    private final PersonMapper personMapper;


    @Autowired
    public JwtController(GetToken getToken, PersonMapper personMapper) {
        this.getToken = getToken;
        this.personMapper = personMapper;
    }

    @PostMapping()
    public PersonDto getTokenHandler(@RequestBody AuthenticationRequest authenticationRequest) {
       if(!EmailUtil.isValidEmail(authenticationRequest.getEmail())) {
           throw new PersonException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_FORMAT_EMAIL_DESCRIPTION);
       } else {
           return personMapper.map(getToken.apply(authenticationRequest));
       }
    }
}
