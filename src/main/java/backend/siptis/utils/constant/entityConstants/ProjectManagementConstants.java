package backend.siptis.utils.constant.entityConstants;

public final class ProjectManagementConstants {

    public static class AreaTable {
        public static final String NAME = "area_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
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
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
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

    public static class ProjectTable {
        public static final String NAME = "project_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class Name {
            public static final String NAME = "name_";
        }

        public static class PerfilPath {
            public static final String NAME = "perfil_path_";
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

        public static class Period {
            public static final String NAME = "period_";
        }

        public static class JoinDefense {
            public static final String NAME = "defense_id_";
            public static final String MAPPED_PROJECT = "project";
        }

        public static class JoinModality {
            public static final String NAME = "modality_id_";
        }

        public static class AreasRelation {
            public static final String NAME = "project_area_";
            public static final String JOIN_COLUMN = "project_id_";
            public static final String INVERSE_JOIN_COLUMN = "area_id_";
        }

        public static class SubAreasRelation {
            public static final String NAME = "project_sub_area_";
            public static final String JOIN_COLUMN = "project_id_";
            public static final String INVERSE_JOIN_COLUMN = "sub_area_id_";
        }

        public static class MappedPresentations {
            public static final String NAME = "project";
        }

        public static class JoinState {
            public static final String NAME = "state_id_";
        }

        public static class MappedActivities {
            public static final String NAME = "project";
        }

        public static class MappedStudents {
            public static final String NAME = "project";
        }

        public static class MappedSupervisors {
            public static final String NAME = "project";
        }

        public static class MappedTutors {
            public static final String NAME = "project";
        }

        public static class MappedTeachers {
            public static final String NAME = "project";
        }

        public static class MappedTribunals {
            public static final String NAME = "project";
        }

        public static class TotalDefensePoints {
            public static final String NAME = "total_defense_points_";
        }
    }

    public static class StateTable {
        public static final String NAME = "state_";

        public static class Id {
            public static final String NAME = "id_";
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
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
            public static final boolean NULLABLE = false;
            public static final boolean UNIQUE = true;
        }

        public static class Name {
            public static final String NAME = "name_";
        }

        public static class MappedProjects {
            public static final String NAME = "subAreas";
        }
    }
}
