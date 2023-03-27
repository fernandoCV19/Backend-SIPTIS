package backend.siptis.service.datosUsuario;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.RespuestaServicio;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;

import java.util.List;

public interface UserInformationService {

    RespuestaServicio findAll();

    RespuestaServicio registerStudent(
            StudentRegisterDTO estudianteDTO, SiptisUser siptisUser);

}
