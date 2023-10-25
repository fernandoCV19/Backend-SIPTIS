package backend.siptis.model.repository.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.dto.UserAreaDTO;
import backend.siptis.model.pjo.dto.UserListItemDTO;
import backend.siptis.model.entity.notifications.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface SiptisUserRepository extends JpaRepository<SiptisUser, Long> {

    @Override
    List<SiptisUser> findAll();

    Optional<SiptisUser> findOneByEmail(String email);

    Optional<SiptisUser> findByTokenPassword(String tokenPassword);

    boolean existsByEmail(String email);

    boolean existsByRolesName(String roleName);

    Optional<SiptisUser> findOneByRolesName(String roleName);

    boolean existsById(Long id);

    boolean existsByTokenPassword(String tokenPassword);

    Optional<SiptisUser> findById(Long id);

    List<SiptisUser> findByRolesName(String roleName);

    Optional<SiptisUser> findOneById(Long id);

    @Query("SELECT a FROM Activity a, ProjectStudent ps " +
            "WHERE ps.student.id = :id AND ps.project.id = a.project.id")
    Page<Activity> findAllPersonalActivities(Long id, Pageable pageable);
    @Query("SELECT a FROM Activity a, ProjectStudent ps " +
            "WHERE ps.student.id = :id AND ps.project.id = a.project.id AND a.activityDate >= :now")
    Page<Activity> findAllPersonalActivities(Long id, @Param("now") Date now, Pageable pageable);
    @Query("SELECT p FROM Project p, ProjectStudent ps " +
            "WHERE ps.student.id = :id AND ps.project.id = p.id")
    Optional <Project> findProjectById(Long id);


    @Query(value ="SELECT su.id, ui.names, ui.lastnames, su.email, ui.codsis, role.name " +
            " FROM siptis_user su, user_information ui,  siptis_user_role sur, role role" +
            " WHERE su.id = ui.user_id AND sur.siptis_user_id = su.id " +
            " AND sur.role_id = role.id AND role.name LIKE :roleName" +
            " AND ( LOWER( ui.names ) LIKE LOWER( CONCAT( '%', :search_name, '%')) " +
            " OR LOWER( ui.lastnames ) LIKE LOWER( CONCAT( '%', :search_name, '%') ))" +
            " ORDER BY ui.lastnames ASC" , nativeQuery = true)
    Page<UserListItemDTO> searchUserList(String search_name, String roleName, Pageable pageable);


    @Query(value ="SELECT DISTINCT su.id, ui.names, ui.lastnames, su.email" +
            " FROM user_information ui" +
            " LEFT JOIN  siptis_user su ON su.id = ui.user_id" +
            " LEFT JOIN siptis_user_role sur ON sur.siptis_user_id = su.id " +
            " LEFT JOIN role role ON sur.role_id = role.id " +
            " WHERE (role.name IS NULL OR role.name NOT IN ('ADMIN', 'STUDENT'))" , nativeQuery = true)
    Page<UserListItemDTO> searchNormalUserList(String search_name, Pageable pageable);


    @Query(value ="SELECT su.id, role.name " +
            " FROM siptis_user su, siptis_user_role sur, role role" +
            " WHERE sur.siptis_user_id = su.id " +
            " AND sur.role_id = role.id AND role.name LIKE :roleName" , nativeQuery = true)
    Page<Object> searchAdminList(String roleName, Pageable pageable);

}
