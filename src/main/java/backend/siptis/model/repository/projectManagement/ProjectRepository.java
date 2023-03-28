package backend.siptis.model.repository.projectManagement;

import java.util.Optional;

import backend.siptis.model.entity.projectManagement.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findById(int id);

}