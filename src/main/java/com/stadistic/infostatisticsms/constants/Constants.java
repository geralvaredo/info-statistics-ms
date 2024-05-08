package com.stadistic.infostatisticsms.constants;

public class Constants {
    public static final String PERSONA = "La persona ";
    public static final String SAVE = " guardada exitosamente";
    public static final String DELETE = " eliminada exitosamente";
    public static final String UPDATE = " actualizada exitosamente";

    //Error Code
    public static final String ERROR_CODE_SAVE_PERSON = "SAVE_PERSON_ERROR";
    public static final String ERROR_CODE_UPDATE_PERSON = "UPDATE_PERSON_ERROR";
    public static final String ERROR_CODE_FIND_PERSON = "NOT_FOUND_PERSON_ERROR";
    public static final String ERROR_CODE_DELETE_PERSON = "DELETE_PERSON_ERROR";
    public static final String ERROR_CODE_EXISTS_PERSON = "SAVE_EXISTS_ERROR";
    public static final String ERROR_CODE_DELETE_NOT_EXIST_PERSON = "DELETE_PERSON_NOT_EXIST_ERROR";
    public static final String ERROR_CODE_UPDATE_NOT_EXIST_PERSON = "UPDATE_PERSON_NOT_EXIST_ERROR";
    public static final String ERROR_CODE_SAVE_UNDER_EIGHTEEN_PERSON = "SAVE_PERSON_UNDER_EIGHTEEN_ERROR";
    public static final String ERROR_CODE_RELATION_EXISTS_PERSON = "RELATION_EXISTS_ERROR";

    //Error Description
    public static final String ERROR_DESCRIPTION_SAVE_PERSON = "Error al guardar la persona, intentelo nuevamente";
    public static final String ERROR_DESCRIPTION_SAVE_RELATION_PERSON = "Error al guardar la relacion, intentelo nuevamente";
    public static final String ERROR_DESCRIPTION_UPDATE_PERSON = "Error al actualizar la persona, intentelo nuevamente";
    public static final String ERROR_DESCRIPTION_FIND_PERSON = "Error, No se encuentra la persona";
    public static final String ERROR_DESCRIPTION_DELETE_PERSON = "Error al eliminar la persona, intentelo nuevamente";
    public static final String ERROR_DESCRIPTION_EXISTS_PERSON = "El usuario ya existe, intente con otro usuario";
    public static final String ERROR_DESCRIPTION_NOT_EXIST_PERSON = "esa persona no existe, intentelo con otro usuario";
    public static final String ERROR_DESCRIPTION_SAVE_UNDER_EIGHTEEN_PERSON = "esa persona no es mayor a 18 años, ingrese otra fecha";
    public static final String ERROR_DESCRIPTION_RELATION_EXISTS_PERSON = "Error, esa relacion ya existe, intente con otros usuarios";

    private Constants() {
    }
}
