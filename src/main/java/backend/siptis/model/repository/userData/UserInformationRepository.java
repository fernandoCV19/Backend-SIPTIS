package backend.siptis.model.repository.userData;

import backend.siptis.model.entity.userData.UserInformation;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserInformationRepository extends JpaRepository<UserInformation, Integer> {


    boolean existsByCi(String ci);

    boolean existsByCodSIS(String codSIS);

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
