package com.stadistic.infostatisticsms.config.auth;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stadistic.infostatisticsms.exception.ErrorDto;
import com.stadistic.infostatisticsms.exception.PersonException;
import com.stadistic.infostatisticsms.utils.CurrentCredential;
import com.stadistic.infostatisticsms.constants.Constants;


import com.stadistic.infostatisticsms.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;




@Component
@Order(0)
public class AuthenticationFilter  extends OncePerRequestFilter {

    private final CurrentCredential currentCredential;


    @Autowired
    public AuthenticationFilter(CurrentCredential currentCredential) {
        this.currentCredential = currentCredential;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        if(!request.getRequestURI().equals("/auth")) {
            if(Objects.nonNull(request.getHeader(Constants.AUTHORIZATION))) {
                String authorization = request.getHeader(Constants.AUTHORIZATION);
                currentCredential.setUserCredentials(authorization);
            } else {
                errorFormatEmail(response, Constants.TOKEN_EXCEPTION_MESSAGE);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void errorFormatEmail(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorDto errorDto = ErrorDto.builder().message(message).build();
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorDto));
    }

}





