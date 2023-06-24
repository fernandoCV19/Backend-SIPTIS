package backend.siptis.service.userData.registerUser;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.pjo.dto.TeacherRegisterDTO;

public interface RegisterUserInformation {

    ServiceAnswer registerStudent(
            StudentRegisterDTO estudianteDTO, SiptisUser siptisUser);

    ServiceAnswer registerTeacherInformation(
            TeacherRegisterDTO teacherDTO, SiptisUser siptisUser);

}
