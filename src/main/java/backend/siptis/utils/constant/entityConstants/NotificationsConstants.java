package backend.siptis.utils.constant.entityConstants;

public final class NotificationsConstants {

    public static class ActivityTable {
        public static final String NAME = "activity_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class ActivityName {
            public static final String NAME = "activity_name_";
        }

        public static class ActivityDescription {
            public static final String NAME = "activity_description_";
        }

        public static class ActivityDate {
            public static final String NAME = "activity_date_";
        }

        public static class JoinProject {
            public static final String NAME = "project_id_";
            public static final boolean NULLABLE = false;
        }
    }

    public static class GeneralActivityTable {
        public static final String NAME = "general_activity_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class ActivityName {
            public static final String NAME = "activity_name_";
        }

        public static class ActivityDescription {
            public static final String NAME = "activity_description_";
        }

        public static class ActivityDate {
            public static final String NAME = "activity_date_";
        }
    }
}
