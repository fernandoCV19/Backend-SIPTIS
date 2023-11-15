package backend.siptis.utils.constant.entityConstants;

public final class UserDataConstants {

    public static class DocumentTable {
        public static final String NAME = "document_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }
        public static class Path {
            public static final String NAME = "path_";
        }
        public static class Type {
            public static final String NAME = "type_";
        }
        public static class Description {
            public static final String NAME = "description_";
        }
        public static class JoinSiptisUser {
            public static final String NAME = "user_id_";
            public static final Boolean NULLABLE = false;
        }
    }

    public static class ScheduleTable {
        public static final String NAME = "schedule_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }
        public static class Day {
            public static final String NAME = "day_";
        }

        public static class HourStart {
            public static final String NAME = "hour_start_";
        }

        public static class HourFinish {
            public static final String NAME = "hour_finish_";
        }
        public static class JoinSiptisUser {
            public static final String NAME = "user_id_";
            public static final Boolean NULLABLE = false;
        }
    }

    public static class UserAreaTable {
        public static final String NAME = "user_area_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }

        public static class Name {
            public static final String NAME = "name_";
        }

        public static class MappedSiptisUsers {
            public static final String NAME = "areas";
        }
    }

    public static class UserCareerTable {
        public static final String NAME = "user_career_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }
        public static class Name {
            public static final String NAME = "name_";
        }
        public static class MappedSiptisUsers {
            public static final String NAME = "career";
        }
    }

    public static class UserInformationTable {
        public static final String NAME = "user_information_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }
        public static class Names {
            public static final String NAME = "names_";
        }
        public static class LastNames {
            public static final String NAME = "last_names_";
        }
        public static class FullName {
            public static final String NAME = "full_name_";
        }
        public static class CellNumber {
            public static final String NAME = "cell_number_";
        }
        public static class CI {
            public static final String NAME = "ci_";
        }
        public static class BirthDate {
            public static final String NAME = "birth_date_";
        }
        public static class CodSIS {
            public static final String NAME = "cod_sis_";
        }
        public static class JoinSiptisUser {
            public static final String NAME = "user_id_";
        }
    }
}
