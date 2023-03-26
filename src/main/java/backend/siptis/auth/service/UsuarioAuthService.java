package backend.siptis.auth.service;

import backend.siptis.auth.entity.Usuario;
import backend.siptis.model.pjo.dto.InformacionEstudianteDTO;
import backend.siptis.model.pjo.dto.RegistrarEstudianteDTO;

import java.util.List;

public interface UsuarioAuthService {
    List<Usuario> findAll();

    InformacionEstudianteDTO registrarEstudiante(RegistrarEstudianteDTO estudianteDTO);
}
