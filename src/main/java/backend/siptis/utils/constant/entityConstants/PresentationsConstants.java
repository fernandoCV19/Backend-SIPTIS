package backend.siptis.utils.constant.entityConstants;

public final class PresentationsConstants {
    public static class PresentationTable {
        public static final String NAME = "presentation_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }
        public static class BlueBookPath {
            public static final String NAME = "blue_book_path_";
        }
        public static class ProjectPath {
            public static final String NAME = "project_path_";
        }
        public static class Phase {
            public static final String NAME = "phase_";
        }
        public static class Reviewed{
            public static final String NAME = "reviewed_";
        }
        public static class JoinProject {
            public static final String NAME = "project_id_";
            public static final Boolean NULLABLE = false;
        }
        public static class MappedReviews {
            public static final String NAME = "presentation";
        }
        public static class Date {
            public static final String NAME = "date_";
        }
    }

    public static class ReviewTable {
        public static final String NAME = "review_";

        public static class Id {
            public static final String NAME = "id_";
            public static final Boolean NULLABLE = false;
            public static final Boolean UNIQUE = true;
        }
        public static class DocumentPath {
            public static final String NAME = "document_path_";
        }
        public static class Commentary {
            public static final String NAME = "commentary_";
        }
        public static class JoinSiptisUser{
            public static final String NAME = "user_id_";
            public static final Boolean NULLABLE = false;
        }
        public static class JoinPresentation {
            public static final String NAME = "presentation_id_";
            public static final Boolean NULLABLE = false;
        }
        public static class Date {
            public static final String NAME = "date_";
        }
    }
}
