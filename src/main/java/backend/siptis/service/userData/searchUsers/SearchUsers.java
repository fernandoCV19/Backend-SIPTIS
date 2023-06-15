package backend.siptis.service.userData.searchUsers;

import backend.siptis.commons.ServiceAnswer;

public interface SearchUsers {

    ServiceAnswer getAllUsers();

    ServiceAnswer getAllAdmin();

    ServiceAnswer getAllStudent();

    ServiceAnswer getAllTeacher();

    ServiceAnswer searchStudentsByName();

    ServiceAnswer searchStudentsByCodSis();

    ServiceAnswer searchStudentsByEmail();

    ServiceAnswer searchTeachersByName();

    ServiceAnswer searchTeachersByCodSis();

    ServiceAnswer searchTeachersByEmail();

    ServiceAnswer searchAdminByEmail();

    ServiceAnswer searchUserByNameAndRole(String email, Long role_id);


}
