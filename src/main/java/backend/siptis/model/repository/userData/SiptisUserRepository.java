package backend.siptis.model.repository.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.records.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiptisUserRepository extends JpaRepository<SiptisUser, Long> {

    @Override
    List<SiptisUser> findAll();

    Optional<SiptisUser> findOneByEmail(String email);

    Optional<SiptisUser> findByEmail(String email);

    Optional<SiptisUser> findByTokenPassword(String tokenPassword);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    boolean existsByTokenPassword(String tokenPassword);

    Optional<SiptisUser> findById(Long id);
    @Query("SELECT a FROM Activity a, ProjectStudent ps " +
            "WHERE ps.student.id = :id AND ps.project.id = a.project.id")
    Page<Activity> findAllPersonalActivities(Long id, Pageable pageable);
}
