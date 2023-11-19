package backend.siptis.utils.constant.entityConstants;

public final class PhaseManagementConstants {
    public static class DocumentPhaseTable {
        public static final String NAME = "document_phase_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class Path {
            public static final String NAME = "path_";
        }

        public static class Name {
            public static final String NAME = "name_";
        }

        public static class Description {
            public static final String NAME = "description_";
        }

        public static class JoinPhase {
            public static final String NAME = "phase_id_";
            public static final boolean NULLABLE = false;
        }
    }

    public static class PhaseTable {
        public static final String NAME = "phase_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class Name {
            public static final String NAME = "name_";
        }

        public static class DescriptionPhaseSort {
            public static final String NAME = "description_phase_short_";
            public static final int MAX_SIZE = 100000;
        }

        public static class DescriptionPhaseLong {
            public static final String NAME = "description_phase_long_";
            public static final int MAX_SIZE = 100000;
        }

        public static class NumberPhase {
            public static final String NAME = "number_phase_";
        }

        public static class JoinModality {
            public static final String NAME = "modality_id_";
        }

        public static class MappedDocuments {
            public static final String NAME = "phase";
        }
    }
}
