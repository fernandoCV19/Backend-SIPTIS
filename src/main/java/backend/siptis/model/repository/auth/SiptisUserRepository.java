package backend.siptis.model.repository.auth;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.pjo.dto.stadisticsDTO.StudentsByCareerDTO;
import backend.siptis.model.pjo.dto.userDataDTO.UserListItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
            "WHERE ps.student.id = :id AND ps.project.id = a.project.id AND a.activityDate >= :now")
    Page<Activity> findAllPersonalActivities(Long id, @Param("now") Date now, Pageable pageable);

    @Query("SELECT p FROM Project p, ProjectStudent ps " +
            "WHERE ps.student.id = :id AND ps.project.id = p.id")
    Optional<Project> findProjectById(Long id);

    @Query(value = "SELECT su.id_, ui.names_, ui.last_names_, su.email_, ui.cod_sis_, role.name_ " +
            " FROM siptis_user_ su, user_information_ ui,  siptis_user_role_ sur, role_ role" +
            " WHERE su.id_ = ui.user_id_ AND sur.siptis_user_id_ = su.id_ " +
            " AND sur.role_id_ = role.id_ AND role.name_ LIKE :roleName" +
            " AND ( LOWER( ui.names_ ) LIKE LOWER( CONCAT( '%', :searchName, '%')) " +
            " OR LOWER( ui.last_names_ ) LIKE LOWER( CONCAT( '%', :searchName, '%') ))" +
            " ORDER BY ui.last_names_ ASC", nativeQuery = true)
    Page<UserListItemDTO> searchUserList(String searchName, String roleName, Pageable pageable);


    @Query(value = "SELECT DISTINCT su.id_, ui.names_, ui.last_names_, su.email_" +
            " FROM user_information_ ui" +
            " LEFT JOIN  siptis_user_ su ON su.id_ = ui.user_id_" +
            " LEFT JOIN siptis_user_role_ sur ON sur.siptis_user_id_ = su.id_ " +
            " LEFT JOIN role_ role ON sur.role_id_ = role.id_ " +
            " WHERE (role.name_ IS NULL OR role.name_ NOT IN ('ADMIN', 'STUDENT')) " +
            " AND ( LOWER( ui.names_ ) LIKE LOWER( CONCAT( '%', :searchName, '%')) " +
            " OR LOWER( ui.last_names_ ) LIKE LOWER( CONCAT( '%', :searchName, '%') )) " +
            " ORDER BY ui.last_names_ ASC"
            , nativeQuery = true)
    Page<UserListItemDTO> searchNormalUserList(String searchName, Pageable pageable);


    @Query(value = "SELECT su.id_, su.email_ " +
            " FROM siptis_user_ su, siptis_user_role_ sur, role_ role" +
            " WHERE sur.siptis_user_id_ = su.id_ " +
            " AND sur.role_id_ = role.id_ AND role.name_ = 'ADMIN'" +
            " AND su.email_ LIKE LOWER( CONCAT( '%', :userEmail ,'%')) ", nativeQuery = true)
    Page<UserListItemDTO> searchAdminList(String userEmail, Pageable pageable);

    @Query(value = "SELECT " +
            "COUNT(DISTINCT CASE WHEN uc.id_ = :id THEN su.id_ END) AS careerStudents, " +
            "COUNT(DISTINCT CASE WHEN uc.id_ <> :id THEN su.id_ END) AS otherStudents  " +
            " FROM siptis_user_ su " +
            " LEFT JOIN " +
            " siptis_user_career_ suc ON suc.siptis_user_id_ = su.id_" +
            " LEFT JOIN " +
            " user_career_ uc ON uc.id_ = suc.career_id_ ", nativeQuery = true)
    List<StudentsByCareerDTO> getNumberOfStudentsInCareer(Long id);

    @Query(value = "SELECT SUBSTRING (info.cod_sis_, 1, 4) AS userYear, COUNT(siptis_user.id_) AS cant " +
            " FROM siptis_user_ siptis_user, siptis_user_career_ suc, " +
            " user_information_ info " +
            " WHERE info.user_id_ = siptis_user.id_ AND siptis_user.id_ =  suc.siptis_user_id_" +
            " AND suc.career_id_ = :careerId " +
            " GROUP BY (userYear) ORDER BY userYear ASC ", nativeQuery = true)
    List<Object> getNumberOfStudentsByYearAndCareer(Long careerId);
}
