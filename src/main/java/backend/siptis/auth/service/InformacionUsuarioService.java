package backend.siptis.auth.service;

import backend.siptis.auth.entity.User;
import backend.siptis.model.pjo.dto.InformacionEstudianteDTO;
import backend.siptis.model.pjo.dto.RegistrarEstudianteDTO;

import java.util.List;

public interface InformacionUsuarioService {

    List<User> findAll();

    InformacionEstudianteDTO registrarEstudiante(
            RegistrarEstudianteDTO estudianteDTO, User user);

}
