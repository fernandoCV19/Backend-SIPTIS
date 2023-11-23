package backend.siptis.utils.constant.entity_constants;

public final class AuthConstants {
    public static class RefreshTokenTable {
        public static final String NAME = "refresh_token_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class JoinSiptisUser {
            public static final String NAME = "siptis_user_id_";
            public static final String REFERENCED_COLUMN = "id_";
        }

        public static class Token {
            public static final String NAME = "token_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class ExpireDate {
            public static final String NAME = "expire_date_";
            public static final boolean NULLABLE = false;
        }
    }

    public static class RoleTable {
        public static final String NAME = "role_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class Name {
            public static final String NAME = "name_";
        }

        public static class MappedSiptisUsers {
            public static final String NAME = "roles";
        }
    }

    public static class SiptisUserTable {
        public static final String NAME = "siptis_user_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class Email {
            public static final String NAME = "email_";
        }

        public static class Password {
            public static final String NAME = "password_";
        }

        public static class TokenPassword {
            public static final String NAME = "token_password_";
        }

        public static class RolesRelation {
            public static final String NAME = "siptis_user_role_";
            public static final String JOIN_COLUMN = "siptis_user_id_";
            public static final String INVERSE_JOIN_COLUMN = "role_id_";
        }

        public static class AreasRelation {
            public static final String NAME = "siptis_user_area_";
            public static final String JOIN_COLUMN = "siptis_user_id_";
            public static final String INVERSE_JOIN_COLUMN = "area_id_";
        }

        public static class CareersRelation {
            public static final String NAME = "siptis_user_career_";
            public static final String JOIN_COLUMN = "siptis_user_id_";
            public static final String INVERSE_JOIN_COLUMN = "career_id_";
        }

        public static class MappedUserInformation {
            public static final String NAME = "siptisUser";
        }

        public static class MappedRefreshToken {
            public static final String NAME = "siptisUser";
        }

        public static class MappedAvailableSchedules {
            public static final String NAME = "siptisUser";
        }

        public static class MappedDocuments {
            public static final String NAME = "siptisUser";
        }

        public static class MappedStudents {
            public static final String NAME = "student";
        }

        public static class MappedSupervisorOf {
            public static final String NAME = "supervisor";
        }

        public static class MappedTutorOf {
            public static final String NAME = "tutor";
        }

        public static class MappedTeacherOf {
            public static final String NAME = "teacher";
        }

        public static class MappedTribunalOf {
            public static final String NAME = "tribunal";
        }

        public static class MappedReviews {
            public static final String NAME = "siptisUser";
        }
    }
}
