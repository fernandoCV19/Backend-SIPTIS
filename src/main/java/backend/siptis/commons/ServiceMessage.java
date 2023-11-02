package backend.siptis.commons;

public enum ServiceMessage {
    NO_PROJECTS,
    WITHOUT_PROJECTS, OK, ID_DOES_NOT_EXIST, NOT_FOUND,
    PRESENTATION_CREATED, PENDING_PRESENTATION, PRESENTATION_DELETED ,CLOUD_OPERATION_COMPLETE, ERROR,
    NO_PRESENTATIONS, NO_FILE_ATTACHED,
    DOCUMENT_GENERATED, NO_DOCUMENTS, DOCUMENT_DELETED,

    EMAIL_NOT_EXIST, INVALID_TOKEN,
    PROJECT_ID_DOES_NOT_EXIST, PROJECT_NAME_ALREADY_EXIST,
    ID_REVIEWER_DOES_NOT_MATCH_WITH_PROJECT, THERE_IS_NO_PRESENTATION_YET, PROJECT_HAS_ALREADY_BEEN_ACCEPTED, PROJECT_IS_ALREADY_NOT_ACCEPTED, PROJECT_IS_ON_ANOTHER_PHASE, PRESENTACION_REVISADA,
    PROJECT_HAS_ALREADY_A_DEFENSE_DATE,
    USER_ID_DOES_NOT_EXIST,

    ERROR_REGISTER_ACCOUNT,ERROR_REGISTER_ACCOUNT_EMAIL, ERROR_REGISTER_ACCOUNT_CI,
    ERROR_REGISTER_ACCOUNT_CODSIS,
    ERROR_VALIDATION, EMAIL_ALREADY_EXIST, COD_SIS_ALREADY_EXIST, CI_ALREADY_EXIST,
    ERROR_LOG_IN, ERROR_BAD_CREDENTIALS,

    SUCCESSFUL_USER_REGISTER,

    USER_IS_NOT_A_TRIBUNAL,  ID_PLACE_DOES_NOT_EXIST, THERE_IS_ANOTHER_RESERVATION_TOO_CLOSE, ID_TRIBUNAL_DOES_NOT_MATCH_WITH_PROJECT, DEFENSE_HAS_NOT_STARTED, DEFENSE_HAS_FINISHED, SCORE_IS_NOT_VALID, THERE_IS_A_PROBLEM_WITH_THE_CLOUD,
    USER_DELETED, USER_AREA_DELETED, AREA_DELETED, ERROR_CANNOT_DELETE_USER,

    SEMESTER_STARTED, SEMESTER_ENDED, SEMESTER_DATE_EDITED, NO_CURRENT_SEMESTER, SEMESTER_ALREADY_EXIST,
    SEMESTER_INFORMATION,

    NO_CURRENT_DIRECTOR,

    CANNOT_GENERATE_LETTER, NOT_APPROVED,

    MODALITY_DOES_NOT_EXIST,


}

