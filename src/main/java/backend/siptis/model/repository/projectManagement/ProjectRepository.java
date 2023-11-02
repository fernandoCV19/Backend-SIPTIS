package backend.siptis.model.repository.projectManagement;

import java.util.List;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.projectManagement.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import backend.siptis.model.pjo.dto.ProjectInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT sup.supervisor.id AS idUser FROM ProjectSupervisor sup WHERE sup.project.id = :projectID"
            + " UNION " +
            "SELECT tea.teacher.id AS idUser FROM ProjectTeacher tea WHERE tea.project.id = :projectID"
            + " UNION " +
            "SELECT tri.tribunal.id AS idUser FROM ProjectTribunal tri WHERE tri.project.id = :projectID"
            + " UNION " +
            "SELECT tut.tutor.id AS idUser FROM ProjectTutor tut WHERE tut.project.id = :idProject" )
    List<Long> getIdsListFromReviewers(Long idProject);

    List<Project> findByPhaseAndTribunalsTribunalId(String phase, Long userId);

    @Query("SELECT p FROM Project p WHERE p.defense IS NOT NULL")
    Page <Project> findAllWithDefense(Pageable pageable);

    @Query("SELECT p FROM Project p WHERE p.defense IS NOT NULL AND UPPER(p.name) LIKE CONCAT('%', UPPER(:name), '%')")
    Page <Project> findAllByNameWithDefense(Pageable pageable, @Param("name") String name);

    @Query("SELECT p FROM Project p " +
            "JOIN p.modality m " +
            "WHERE p.defense IS NOT NULL " +
            "AND UPPER(m.name) LIKE CONCAT('%', UPPER(:modality), '%')")
    Page <Project> findAllByModalityWithDefense(Pageable pageable, @Param("modality") String modality);

    @Query("SELECT p FROM Project p " +
            "JOIN p.areas a " +
            "WHERE p.defense IS NOT NULL " +
            "AND UPPER(a.name) LIKE CONCAT('%', UPPER(:area), '%')")
    Page <Project> findAllByAreaWithDefense(Pageable pageable, @Param("area") String area);

    @Query("SELECT p FROM Project p " +
            "JOIN p.subAreas s " +
            "WHERE p.defense IS NOT NULL " +
            "AND UPPER(s.name) LIKE CONCAT('%', UPPER(:subarea), '%')")
    Page <Project> findAllBySubAreaWithDefense(Pageable pageable, @Param("subarea") String area);

    @Query("SELECT DISTINCT p FROM Project p " +
            "JOIN p.modality m " +
            "JOIN p.areas a " +
            "JOIN p.subAreas s " +
            "WHERE p.defense IS NOT NULL " +
            "AND CASE WHEN :name = NULL THEN 1=1 ELSE UPPER(p.name) LIKE CONCAT('%', UPPER(:name), '%') END  " +
            "AND CASE WHEN :modality = NULL THEN 1=1 ELSE UPPER(m.name) LIKE CONCAT('%', UPPER(:modality), '%') END " +
            "AND CASE WHEN :area = NULL THEN 1=1 ELSE UPPER(a.name) LIKE CONCAT('%', UPPER(:area), '%') END " +
            "AND CASE WHEN :subarea = NULL THEN 1=1 ELSE UPPER(s.name) LIKE CONCAT('%', UPPER(:subarea), '%') END ")
    Page <Project> findAllWithFilters(Pageable pageable, @Param("name") String name, @Param("modality") String modality, @Param("area") String area, @Param("subarea") String subarea);

    @Query(value = "SELECT  project.id AS id, project.name AS name, " +
            "project.perfil_path AS perfil, modality.name AS modality," +
            " modality.id AS modalityId  " +
            " FROM project project, modality modality " +
            " WHERE project.modality_id = modality.id ORDER BY project.name ASC", nativeQuery = true )
    List<ProjectInfoDTO> getProjectsList();


    boolean existsByName(String name);

    @Query(value = "SELECT  project.id AS id, project.name AS name, " +
            "project.perfil_path AS perfil, modality.name AS modality," +
            " modality.id AS modalityId  " +
            " FROM project project, modality modality " +
            " WHERE project.modality_id = modality.id " +
            " AND LOWER( project.name ) LIKE LOWER( CONCAT( '%', :search, '%'))  ", nativeQuery = true )
    Page <ProjectInfoDTO> searchProject(String search, Pageable pageable );


    @Query(value ="SELECT modality.name AS modalityName, COUNT(projectId) AS cant FROM " +
            "( SELECT project.id AS projectId, project.modality_id AS modalityId " +
            " FROM project project, siptis_user_career siptis_user_career," +
            " project_student project_student " +
            " WHERE project.id = project_student.project_id AND " +
            " project_student.user_id =  siptis_user_career.siptisuser_id " +
            "AND siptis_user_career.career_id = :idCareer GROUP BY (project.id) " +
            " ) result" +
            " RIGHT JOIN modality modality ON result.modalityId = modality.id " +

            " GROUP BY (modalityName) " , nativeQuery = true)
    List<Object> getNumberOfProyectsByModalityAndCareer(long idCareer);


    @Query(value ="SELECT project.period, COUNT(project.period) AS cant " +
            " FROM project project, project_student ps, siptis_user su, siptis_user_career suc " +
            " WHERE project.id = ps.project_id AND ps.user_id = su.id " +
            " AND suc.siptisuser_id = su.id AND suc.career_id = :idCareer "+
            " GROUP BY (project.period) " , nativeQuery = true)
    List<Object> getNumberProjectsByPeriodAndCareer(Long idCareer);

}