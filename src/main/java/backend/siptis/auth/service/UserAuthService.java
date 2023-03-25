package backend.siptis.auth.service;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.pjo.dto.InformacionEstudianteDTO;
import backend.siptis.model.pjo.dto.RegistrarEstudianteDTO;

import java.util.List;

public interface UserAuthService {
    List<SiptisUser> findAll();

    InformacionEstudianteDTO registerStudent(RegistrarEstudianteDTO estudianteDTO);
}
