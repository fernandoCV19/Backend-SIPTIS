package backend.siptis.utils.constant.entityConstants;

public final class DefenseManagementConstants {

    public static class DefenseTable {
        public static final String NAME = "defense_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class JoinPlaceToDefense {
            public static final String NAME = "place_to_defense_id_";
            public static final boolean NULLABLE = false;
        }

        public static class JoinProject {
            public static final String NAME = "project_id_";
            public static final boolean NULLABLE = false;
        }

        public static class Date {
            public static final String NAME = "date_";
        }

        public static class StartTime {
            public static final String NAME = "start_time_";
        }

        public static class EndTime {
            public static final String NAME = "end_time_";
        }

        public static class SubstituteName {
            public static final String NAME = "substitute_name_";
        }
    }

    public static class PlaceToDefenseTable {
        public static final String NAME = "place_to_defense_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class Name {
            public static final String NAME = "name_";
        }

        public static class Location {
            public static final String NAME = "location_";
        }

        public static class Capacity {
            public static final String NAME = "capacity_";
        }

        public static class MappedDefenses {
            public static final String NAME = "placeToDefense";
        }
    }
}
