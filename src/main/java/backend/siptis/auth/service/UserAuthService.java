package backend.siptis.auth.service;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;

import java.util.List;

public interface UserAuthService {
    List<SiptisUser> findAll();

    StudentInformationDTO registerStudent(StudentRegisterDTO estudianteDTO);
}
