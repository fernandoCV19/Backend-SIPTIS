package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.pjo.dto.TeacherRegisterDTO;
import backend.siptis.model.pjo.dto.records.LogInDTO;

import java.util.Optional;

public interface UserAuthService {

    ServiceAnswer findAll();

    ServiceAnswer logIn(LogInDTO logInDTO);

    ServiceAnswer userInfo(Long id);

    Long getIdFromToken(String token);

    Optional<SiptisUser> findByTokenPassword(String tokenPassword);





}
