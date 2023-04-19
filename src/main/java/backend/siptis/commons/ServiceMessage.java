package backend.siptis.commons;

public enum ServiceMessage {
    WITHOUT_PROJECTS, OK, ID_DOES_NOT_EXIST, NOT_FOUND,
    PRESENTACION_CREADA, PRESENTACION_PENDIENTE, PRESENTACION_ELIMINADA ,OPERACION_DE_NUBE_COMPLETADA, ERROR,
    SIN_PRESENTACIONES, NO_FILE_ATTACHED,
    PROJECT_ID_DOES_NOT_EXIST, USER_ID_DOES_NOT_EXIST, ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, THERE_IS_NO_PRESENTATION_YET, PROJECT_HAS_ALREADY_BEEN_ACCEPTED, PROJECT_IS_ALREADY_NOT_ACCEPTED, PROJECT_IS_ON_ANOTHER_PHASE, PRESENTACION_REVISADA,
    ERROR_REGISTRO_CUENTA,ERROR_REGISTRO_CUENTA_EMAIL,ERROR_REGISTRO_CUENTA_CI,
    ERROR_REGISTRO_CUENTA_CODSIS,
    ERROR_INICIO_SESION, ERROR_INICIO_SESION_EMAIL, ERROR_INICIO_SESION_PASSWORD,
}

