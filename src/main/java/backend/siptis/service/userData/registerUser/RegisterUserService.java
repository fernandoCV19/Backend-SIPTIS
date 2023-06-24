package backend.siptis.service.userData.registerUser;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.pjo.dto.TeacherRegisterDTO;

public interface RegisterUserService {

    ServiceAnswer registerStudent(StudentRegisterDTO estudianteDTO);

    ServiceAnswer registerTeacher(TeacherRegisterDTO teacherDTO);

    ServiceAnswer registerAdmin(AdminRegisterDTO adminDTO);
}
