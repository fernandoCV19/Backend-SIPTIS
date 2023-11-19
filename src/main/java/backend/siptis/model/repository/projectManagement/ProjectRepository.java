package backend.siptis.model.repository.projectManagement;

import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.dto.projectManagement.ProjectInfoDTO;
import backend.siptis.model.pjo.dto.stadisticsDTO.ProjectByCareerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT sup.supervisor.id AS idUser FROM ProjectSupervisor sup WHERE sup.project.id = :projectId"
            + " UNION " +
            "SELECT tea.teacher.id AS idUser FROM ProjectTeacher tea WHERE tea.project.id = :projectId"
            + " UNION " +
            "SELECT tri.tribunal.id AS idUser FROM ProjectTribunal tri WHERE tri.project.id = :projectId"
            + " UNION " +
            "SELECT tut.tutor.id AS idUser FROM ProjectTutor tut WHERE tut.project.id = :projectId")
    List<Long> getIdsListFromReviewers(Long projectId);

    @Query("SELECT p FROM Project p WHERE p.defense IS NOT NULL")
    Page<Project> findAllWithDefense(Pageable pageable);

    @Query("SELECT p FROM Project p WHERE p.defense IS NOT NULL AND UPPER(p.name) LIKE CONCAT('%', UPPER(:name), '%')")
    Page<Project> findAllByNameWithDefense(Pageable pageable, @Param("name") String name);

    @Query("SELECT p FROM Project p " +
            "JOIN p.modality m " +
            "WHERE p.defense IS NOT NULL " +
            "AND UPPER(m.name) LIKE CONCAT('%', UPPER(:modality), '%')")
    Page<Project> findAllByModalityWithDefense(Pageable pageable, @Param("modality") String modality);

    @Query("SELECT p FROM Project p " +
            "JOIN p.areas a " +
            "WHERE p.defense IS NOT NULL " +
            "AND UPPER(a.name) LIKE CONCAT('%', UPPER(:area), '%')")
    Page<Project> findAllByAreaWithDefense(Pageable pageable, @Param("area") String area);

    @Query("SELECT p FROM Project p " +
            "JOIN p.subAreas s " +
            "WHERE p.defense IS NOT NULL " +
            "AND UPPER(s.name) LIKE CONCAT('%', UPPER(:subarea), '%')")
    Page<Project> findAllBySubAreaWithDefense(Pageable pageable, @Param("subarea") String area);

    @Query("SELECT DISTINCT p FROM Project p " +
            "JOIN p.modality m " +
            "JOIN p.areas a " +
            "JOIN p.subAreas s " +
            "WHERE p.defense IS NOT NULL " +
            "AND (:name IS NULL OR UPPER(p.name) LIKE CONCAT('%', UPPER(:name), '%'))  " +
            "AND (:period IS NULL OR p.period = :period)")
    Page<Project> standardFilter(Pageable pageable, @Param("name") String name, @Param("period") String period);

    @Query("""
            select distinct p from Project p 
            left join p.subAreas subAreas 
            left join p.areas areas 
            left join p.students students 
            left join p.tutors tutors
            where p.defense is not null 
            and ((?1) is null or upper(p.name) like concat('%',upper(?1),'%')) 
            and ((?2) is null or p.period = ?2)
            and ((?3) is null or upper(p.modality.name) like concat('%',upper(?3),'%'))
            and ((?4) is null or upper(areas.name) like concat('%',upper(?4),'%')) 
            and ((?5) is null or upper(subAreas.name) like concat('%',upper(?5),'%')) 
            and ((?6) is null or upper(students.student.userInformation.fullName) like concat('%',upper(?6),'%')) 
            and ((?7) is null or upper(tutors.tutor.userInformation.fullName) like concat('%',upper(?7),'%')) """)
    Page<Project> advancedFilter(@Nullable String name, @Nullable String period, @Nullable String modality, @Nullable String areas, @Nullable String subAreas, @Nullable String student, @Nullable String tutor, Pageable pageable);

    boolean existsByName(String name);

    @Query(value = "SELECT  project.id_ AS id, project.name_ AS name, " +
            "project.perfil_path_ AS perfil, modality.name_ AS modality," +
            " modality.id_ AS modalityId  " +
            " FROM project_ project, modality_ modality " +
            " WHERE project.modality_id_ = modality.id_ " +
            " AND LOWER( project.name_ ) LIKE LOWER( CONCAT( '%', :search, '%'))  ", nativeQuery = true)
    Page<ProjectInfoDTO> searchProject(String search, Pageable pageable);

    @Query(value = "SELECT modality.name_ AS modality, COUNT(projectId) AS number_projects FROM " +
            "( SELECT project.id_ AS projectId, project.modality_id_ AS modalityId " +
            " FROM project_ project, siptis_user_career_ siptis_user_career," +
            " project_student_ project_student " +
            " WHERE project.id_ = project_student.project_id_ AND " +
            " project_student.user_id_ =  siptis_user_career.siptis_user_id_ " +
            "AND siptis_user_career.career_id_ = :idCareer GROUP BY (project.id_) " +
            " ) result" +
            " RIGHT JOIN modality_ modality ON result.modalityId = modality.id_ " +
            " GROUP BY (modality.name_) ", nativeQuery = true)
    List<Object> getNumberOfProjectsByModalityAndCareer(long idCareer);

    @Query(value = "SELECT area.name_ AS area, COUNT(projectId) AS number_projects FROM " +
            "( SELECT project.id_ AS projectId " +
            " FROM project_ project, siptis_user_career_ siptis_user_career," +
            " project_student_ project_student " +
            " WHERE project.id_ = project_student.project_id_ AND " +
            " project_student.user_id_ =  siptis_user_career.siptis_user_id_ " +
            "AND siptis_user_career.career_id_ = :idCareer GROUP BY (project.id_) " +
            " ) result" +
            " RIGHT JOIN project_area_ pa ON pa.project_id_ = result.projectId " +
            " RIGHT JOIN area_ area ON area.id_ = pa.area_id_" +
            " GROUP BY (area.name_) ORDER BY area.name_ ASC ", nativeQuery = true)
    List<Object> getNumberOfProjectsByAreasAndCareer(long idCareer);

    @Query(value = "SELECT sub_area.name_ AS sub_area, COUNT(projectId) AS number_projects FROM " +
            "( SELECT project.id_ AS projectId " +
            " FROM project_ project, siptis_user_career_ siptis_user_career," +
            " project_student_ project_student " +
            " WHERE project.id_ = project_student.project_id_ AND " +
            " project_student.user_id_ =  siptis_user_career.siptis_user_id_ " +
            "AND siptis_user_career.career_id_ = :idCareer GROUP BY (project.id_) " +
            " ) result" +
            " RIGHT JOIN project_area_ pa ON pa.project_id_ = result.projectId " +
            " RIGHT JOIN sub_area_ sub_area ON sub_area.id_ = pa.area_id_" +
            " GROUP BY (sub_area.name_) ORDER BY sub_area.name_ ASC ", nativeQuery = true)
    List<Object> getNumberOfProjectsBySubAreasAndCareer(long idCareer);

    @Query(value = "SELECT " +
            "COUNT(DISTINCT CASE WHEN uc.id_ = :idCareer THEN p.id_ END) AS careerProjects, " +
            "COUNT(DISTINCT CASE WHEN uc.id_ <> :idCareer THEN p.id_ END) AS otherCareerProjects  " +
            " FROM project_ p " +
            " LEFT JOIN project_student_ ps ON ps.project_id_ = p.id_ " +
            " LEFT JOIN siptis_user_ su ON ps.user_id_ = su.id_ " +
            " LEFT JOIN siptis_user_career_ suc ON suc.siptis_user_id_ = su.id_" +
            " LEFT JOIN " +
            " user_career_ uc ON uc.id_ = suc.career_id_ ", nativeQuery = true)
    List<ProjectByCareerDTO> getProjectsByCareer(long idCareer);

    @Query(value = "SELECT SUBSTRING(project.period_, 3, 6) AS projectYear, COUNT(project.id_) AS cant " +
            " FROM project_ project, project_student_ ps, siptis_user_ su, siptis_user_career_ suc " +
            " WHERE project.id_ = ps.project_id_ AND ps.user_id_ = su.id_ " +
            " AND suc.siptis_user_id_ = su.id_ AND suc.career_id_ = :idCareer " +
            " GROUP BY (projectYear) ", nativeQuery = true)
    List<Object> getNumberProjectsByPeriodAndCareer(Long idCareer);

}