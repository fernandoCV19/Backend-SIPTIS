package backend.siptis.model.repository.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.userData.UserInformation;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import backend.siptis.model.pjo.dto.UserListItemDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {


    boolean existsByCi(String ci);

    boolean existsByCodSIS(String codSIS);


    @Query(value ="SELECT su.id, ui.names, ui.lastnames" +
            " FROM siptis_user su, user_information ui, role r, siptis_user_role sur " +
            " WHERE su.id = ui.user_id AND su.id = sur.siptis_user_id " +
            "AND r.id = sur.role_id AND r.id = :role_id AND LOWER( ui.names ) "+
            " LIKE LOWER( CONCAT( '%', :search_name, '%') ) " , nativeQuery = true)
    List<UserListItemDTO> searchUserByNameAndRole(String search_name, Long role_id);

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


}
