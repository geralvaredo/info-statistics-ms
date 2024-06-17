package com.stadistic.infostatisticsms.constants;

public class Constants {

    //Request Headers
    public static final String AUTHORIZATION = "Authorization";
    public static final String TOKEN_EXCEPTION_MESSAGE =
            "The access token provided is expired, revoked, malformed, or invalid for other reasons";
    //Error Code


    //Error Description
    public static final String ERROR_DESCRIPTION_SAVE_PERSON = "Error al guardar la persona, intentelo nuevamente";

    public static final String ERROR_DESCRIPTION_EXISTS_PERSON = "El usuario ya existe, intente con otro usuario";
    public static final String ERROR_FORMAT_EMAIL_DESCRIPTION = "El correo no posse el formato correcto";


    private Constants() {
    }
}
