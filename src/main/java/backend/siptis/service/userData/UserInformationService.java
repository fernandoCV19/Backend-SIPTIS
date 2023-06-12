package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.pjo.dto.TeacherRegisterDTO;

public interface UserInformationService {

    ServiceAnswer findAll();

    /*ServiceAnswer registerStudent(
            StudentRegisterDTO estudianteDTO, SiptisUser siptisUser);

    ServiceAnswer registerTeacher(
            TeacherRegisterDTO teacherDTO, SiptisUser siptisUser);

    boolean existByCodSIS(String codSIS);

    boolean existByCi(String ci);*/
}
