package backend.siptis.model.repository.projectManagement;

import java.util.List;

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
    @Query("SELECT sup.supervisor.id AS idUser FROM ProjectSupervisor sup WHERE sup.project.id = :projectId"
            + " UNION " +
            "SELECT tea.teacher.id AS idUser FROM ProjectTeacher tea WHERE tea.project.id = :projectId"
            + " UNION " +
            "SELECT tri.tribunal.id AS idUser FROM ProjectTribunal tri WHERE tri.project.id = :projectId"
            + " UNION " +
            "SELECT tut.tutor.id AS idUser FROM ProjectTutor tut WHERE tut.project.id = :projectId" )
    List<Long> getIdsListFromReviewers(Long projectId);

    List<Project> findByPhase(String phase);

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
            " WHERE project.modality_id = modality.id ", nativeQuery = true )
    List<ProjectInfoDTO> getProjectsList();


    boolean existsByName(String name);

    @Query(value = "SELECT  project.id AS id, project.name AS name, " +
            "project.perfil_path AS perfil, modality.name AS modality," +
            " modality.id AS modalityId  " +
            " FROM project project, modality modality " +
            " WHERE project.modality_id = modality.id " +
            " AND LOWER( project.name ) LIKE LOWER( CONCAT( '%', :search, '%'))  ", nativeQuery = true )
    Page <ProjectInfoDTO> searchProject(String search, Pageable pageable );

    List<Project> findByTribunals_Tribunal_IdAndPhase(Long tribunal, String phase);


}