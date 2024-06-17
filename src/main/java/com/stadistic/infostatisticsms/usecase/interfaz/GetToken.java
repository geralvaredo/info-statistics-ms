package com.stadistic.infostatisticsms.usecase.interfaz;

import com.stadistic.infostatisticsms.dto.AuthenticationRequest;

@FunctionalInterface
public interface GetToken {
    String apply(AuthenticationRequest authenticationRequest);
}
