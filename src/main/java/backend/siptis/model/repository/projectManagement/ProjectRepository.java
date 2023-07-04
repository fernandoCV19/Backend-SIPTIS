package backend.siptis.model.repository.projectManagement;

import java.util.List;
import java.util.Optional;

import backend.siptis.model.entity.projectManagement.Project;

import backend.siptis.model.pjo.dto.ProjectInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT sup.supervisor.id AS idUser FROM ProjectSupervisor sup WHERE sup.project.id = :idProject"
            + " UNION " +
            "SELECT tea.teacher.id AS idUser FROM ProjectTeacher tea WHERE tea.project.id = :idProject"
            + " UNION " +
            "SELECT tri.tribunal.id AS idUser FROM ProjectTribunal tri WHERE tri.project.id = :idProject"
            + " UNION " +
            "SELECT tut.tutor.id AS idUser FROM ProjectTutor tut WHERE tut.project.id = :idProject" )
    List<Long> getIdsListFromReviewers(Long idProject);


    @Query(value = "SELECT  project.id AS id, project.name AS name, " +
            "project.perfil_path AS perfil, modality.name AS modality," +
            " modality.id AS modalityId  " +
            " FROM project project, modality modality " +
            " WHERE project.modality_id = modality.id ", nativeQuery = true )
    List<ProjectInfoDTO> getProjectsList();

}