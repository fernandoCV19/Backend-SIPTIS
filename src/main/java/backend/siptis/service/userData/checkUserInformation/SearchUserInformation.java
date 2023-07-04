package backend.siptis.service.userData.checkUserInformation;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;

public interface SearchUserInformation {

    ServiceAnswer findAllUsers();

    ServiceAnswer findAllStudents();

    ServiceAnswer findAllTeachers();

    ServiceAnswer findAllAdmins();

    SiptisUser findByEmail(String email);

    SiptisUser findById(Long id);


    SiptisUser findByTokenPassword(String tokenPassword);

}
