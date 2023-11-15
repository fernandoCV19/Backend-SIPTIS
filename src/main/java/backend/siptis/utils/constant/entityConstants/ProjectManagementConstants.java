package backend.siptis.utils.constant.entityConstants;

public final class ProjectManagementConstants {

    public static class AreaTable {
        public static final String NAME = "area_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }

        public static class Name {
            public static final String NAME = "name_";
        }

        public static class MappedProjects {
            public static final String NAME = "areas";
        }
    }

    public static class ModalityTable {
        public static final String NAME = "modality_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }

        public static class Name {
            public static final String NAME = "name_";
        }

        public static class MappedProjects {
            public static final String NAME = "modality";
        }

        public static class MappedPhases {
            public static final String NAME = "modality";
        }
    }

    public static class StateTable {
        public static final String NAME = "state_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }

        public static class Name {
            public static final String NAME = "name_";
        }

        public static class MappedProjects {
            public static final String NAME = "state";
        }
    }

    public static class SubAreaTable {
        public static final String NAME = "sub_area_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }

        public static class Name {
            public static final String NAME = "name_";
        }

        public static class MappedProjects {
            public static final String NAME = "subAreas";
        }
    }
}
