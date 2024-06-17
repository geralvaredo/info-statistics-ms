package com.stadistic.infostatisticsms.usecase.service;

import com.stadistic.infostatisticsms.dto.AuthenticationRequest;
import com.stadistic.infostatisticsms.usecase.interfaz.GetToken;
import com.stadistic.infostatisticsms.utils.JwtUtil;

import org.springframework.stereotype.Service;


@Service
public class GetTokenImpl implements GetToken {

    @Override
    public String apply(AuthenticationRequest authenticationRequest) {
        return JwtUtil.generateToken(authenticationRequest);
    }
}
