package backend.siptis.model.repository.projectManagement;

import java.util.List;
import java.util.Optional;

import backend.siptis.model.entity.projectManagement.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT sup.supervisor.id AS idUser FROM ProjectSupervisor sup WHERE sup.project.id = :projectID"
            + " UNION " +
            "SELECT tea.teacher.id AS idUser FROM ProjectTeacher tea WHERE tea.project.id = :projectID"
            + " UNION " +
            "SELECT tri.tribunal.id AS idUser FROM ProjectTribunal tri WHERE tri.project.id = :projectID"
            + " UNION " +
            "SELECT tut.tutor.id AS idUser FROM ProjectTutor tut WHERE tut.project.id = :projectID" )
    List<Long> getIdsListFromReviewers(Long projectID);
}