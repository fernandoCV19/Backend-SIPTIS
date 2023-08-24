package backend.siptis.model.repository.general;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.pjo.dto.UserListItemDTO;
import backend.siptis.model.pjo.dto.stadisticsDTO.StudentsByCareerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StadisticsRepository extends JpaRepository<SiptisUser, Integer > {

    @Query(value ="SELECT uc.name AS careerName, COUNT(suc.siptisuser_id) AS cant " +
            " FROM siptis_user_career suc, user_career uc " +
            " WHERE suc.career_id = uc.id" +
            " GROUP BY (careerName) " , nativeQuery = true)
    List<Object> getNumberOfStudentsInCareer();


    @Query(value ="SELECT ua.name AS areaName, COUNT(sua.siptisuser_id) AS cant " +
            " FROM siptis_user_area sua" +
            " RIGHT JOIN user_area ua ON sua.area_id = ua.id " +
            " GROUP BY (areaName) " , nativeQuery = true)
    List<Object> getNumberOfUserInArea();

    @Query(value ="SELECT modality.name AS modalityName, COUNT(projectId) AS cant FROM " +
            "( SELECT project.id AS projectId, project.modality_id AS modalityId " +
            " FROM project project, siptis_user_career siptis_user_career," +
            " project_student project_student " +
            " WHERE project.id = project_student.project_id AND " +
            " project_student.user_id =  siptis_user_career.siptisuser_id " +
            "AND siptis_user_career.career_id = :idCareer GROUP BY (project.id) " +
            " ) result" +
            " RIGHT JOIN modality modality ON result.modalityId = modality.id " +

            " GROUP BY (modalityName) " , nativeQuery = true)
    List<Object> getNumberOfProyectsByAreaAndCareer(int idCareer);

    @Query(value ="SELECT SUBSTRING (info.codsis, 1, 4) AS userYear, COUNT(siptis_user.id) AS cant " +
            " FROM siptis_user siptis_user, siptis_user_role user_role, user_information info" +
            " WHERE siptis_user.id = user_role.siptis_user_id AND " +
            " user_role.role_id = 2 AND info.user_id = siptis_user.id" +
            " GROUP BY (userYear) " , nativeQuery = true)
    List<Object> getNumberOfStudentsByYear();

    @Query(value ="SELECT SUBSTRING (info.codsis, 1, 4) AS userYear, COUNT(siptis_user.id) AS cant " +
            " FROM siptis_user siptis_user, siptis_user_role user_role," +
            " user_information info, siptis_user_career user_career, " +
            " user_career career " +
            " WHERE siptis_user.id = user_role.siptis_user_id AND " +
            " user_role.role_id = 2" +
            " AND siptis_user.id = user_career.siptisuser_id" +
            " AND user_career.career_id = career.id " +
            " AND career.name LIKE CONCAT( '%', :careerName, '%')  AND info.user_id = siptis_user.id" +
            " GROUP BY (userYear) " , nativeQuery = true)
    List<Object> getNumberOfStudentsByYearAndCareer(String careerName);



}
