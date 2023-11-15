package backend.siptis.utils.constant.entityConstants;

public final class EditorsAndReviewersConstants {
    public static class ProjectStudentTable {
        public static final String NAME = "project_student_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class JoinStudent {
            public static final String NAME = "user_id_";
            public static final boolean NULLABLE = false;
        }

        public static class JoinProject {
            public static final String NAME = "project_id_";
            public static final boolean NULLABLE = false;
        }
    }

    public static class ProjectSupervisorTable {
        public static final String NAME = "project_supervisor_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class JoinSupervisor {
            public static final String NAME = "user_id_";
            public static final boolean NULLABLE = false;
        }

        public static class JoinProject {
            public static final String NAME = "project_id_";
            public static final boolean NULLABLE = false;
        }

        public static class Accepted {
            public static final String NAME = "accepted_";
        }

        public static class Reviewed {
            public static final String NAME = "reviewed_";
        }
    }

    public static class ProjectTeacherTable {
        public static final String NAME = "project_teacher_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class JoinTeacher {
            public static final String NAME = "user_id_";
            public static final boolean NULLABLE = false;
        }

        public static class JoinProject {
            public static final String NAME = "project_id_";
            public static final boolean NULLABLE = false;
        }

        public static class Accepted {
            public static final String NAME = "accepted_";
        }

        public static class Reviewed {
            public static final String NAME = "reviewed_";
        }
    }

    public static class ProjectTribunalTable {
        public static final String NAME = "project_tribunal_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class JoinTribunal {
            public static final String NAME = "user_id_";
            public static final boolean NULLABLE = false;
        }

        public static class JoinProject {
            public static final String NAME = "project_id_";
            public static final boolean NULLABLE = false;
        }

        public static class Accepted {
            public static final String NAME = "accepted_";
        }

        public static class Reviewed {
            public static final String NAME = "reviewed_";
        }

        public static class DefensePoints {
            public static final String NAME = "defense_points_";
        }
    }

    public static class ProjectTutorTable {
        public static final String NAME = "project_tutor_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class JoinTutor {
            public static final String NAME = "user_id_";
            public static final boolean NULLABLE = false;
        }

        public static class JoinProject {
            public static final String NAME = "project_id_";
            public static final boolean NULLABLE = false;
        }

        public static class Accepted {
            public static final String NAME = "accepted_";
        }

        public static class Reviewed {
            public static final String NAME = "reviewed_";
        }
    }
}
