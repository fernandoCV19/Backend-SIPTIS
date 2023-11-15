package backend.siptis.utils.constant.entityConstants;

public final class SemesterConstants {

    public static class SemesterInformationTable {
        public static final String NAME = "semester_information_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }
        public static class StartDate {
            public static final String NAME = "start_date_";
        }
        public static class EndDate {
            public static final String NAME = "end_date_";
        }
        public static class Period {
            public static final String NAME = "period_";
        }
        public static class InProgress {
            public static final String NAME = "in_progress_";
        }
    }
}
