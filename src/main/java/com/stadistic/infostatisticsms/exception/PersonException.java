package com.stadistic.infostatisticsms.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@Setter
@Getter
public class PersonException extends NestedRuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final int status;
    private final String messageCode;
    private final String messageDescription;

    public PersonException(HttpStatus status, String messageCode, String messageDescription) {
        super(messageDescription);
        this.status = status.value();
        this.messageCode = messageCode;
        this.messageDescription = messageDescription;
    }
}
