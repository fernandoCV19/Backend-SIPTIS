package backend.siptis.model.repository.userData;

import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.PotentialTribunalDTO;
import backend.siptis.model.pjo.dto.UserAreaDTO;
import backend.siptis.model.pjo.dto.stadisticsDTO.AdminListItemDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import backend.siptis.model.pjo.dto.UserListItemDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {


    boolean existsByCi(String ci);

    boolean existsByCodSIS(String codSIS);

    Optional<UserInformation> findById(Long id);


    @Query(value ="SELECT su.id, ui.names, ui.lastnames, ui.codSIS" +
            " FROM siptis_user su, user_information ui, role r, siptis_user_role sur " +
            " WHERE su.id = ui.user_id AND su.id = sur.siptis_user_id " +
            "AND r.id = sur.role_id AND r.id = :role_id AND LOWER( ui.names ) "+
            " LIKE LOWER( CONCAT( '%', :search_name, '%') ) " , nativeQuery = true)
    List<UserListItemDTO> searchUserByNameAndRole(String search_name, Long role_id);

    @Query(value ="SELECT su.id, ui.names, ui.lastnames, ui.codSIS" +
            " FROM siptis_user su, user_information ui, role r, siptis_user_role sur " +
            " WHERE su.id = ui.user_id AND su.id = sur.siptis_user_id " +
            "AND r.id = sur.role_id AND r.id = :role_id " , nativeQuery = true)
    List<UserListItemDTO> getAllUsersByRole( Long role_id);

    @Query(value ="SELECT su.id, su.email" +
            " FROM siptis_user su, role r, siptis_user_role sur " +
            " WHERE su.id = sur.siptis_user_id " +
            "AND r.id = sur.role_id AND r.id = 2 " , nativeQuery = true)
    List<AdminListItemDTO> getAllAdmins();


    @Query(value ="SELECT CONCAT(ui.names,' ',ui.lastnames)" +
            "FROM user_information ui " +
            "WHERE ui.user_id IN (" +
            "    SELECT pt.user_id " +
            "    FROM project_teacher pt " +
            "    WHERE pt.project_id = :idProject" +
            ")", nativeQuery = true)
    List<String> getTeachersNames (Long idProject);

    @Query(value = "SELECT CONCAT(ui.names,' ',ui.lastnames)" +
            "FROM user_information ui " +
            "WHERE ui.user_id IN (" +
            "    SELECT pt.user_id " +
            "    FROM project_tutor pt " +
            "    WHERE pt.project_id = :idProject" +
            ")", nativeQuery = true)
    List <String> getTutorsNames (Long idProject);

    @Query(value = "SELECT ua.id, ua.name " +
            "FROM user_area ua " +
            "WHERE ua.id NOT IN (" +
            "    SELECT ua2.id " +
            "    FROM user_area ua2, siptis_user_area sua " +
            "    WHERE ua2.id = sua.area_id AND sua.siptisuser_id = :id" +
            ")", nativeQuery = true)
    List <UserAreaDTO> getNotSelectedAreas (Long id);

    @Modifying
    @Query(value = "DELETE FROM siptis_user_area sua " +
            "WHERE sua.siptisuser_id = :userId", nativeQuery = true)
    void deleteUserAreas(Long userId);

    @Query(value ="SELECT su.id, ui.names, ui.lastnames " +
            " FROM siptis_user su, user_information ui," +
            " role r, siptis_user_role sur " +
            " WHERE su.id = sur.siptis_user_id " +
            "AND r.id = sur.role_id AND r.id = 3 " +
            "AND su.id = ui.user_id" , nativeQuery = true)
    List<PotentialTribunalDTO> getTeachersInformation();
}
