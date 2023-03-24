package backend.siptis.auth.service;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.pjo.dto.InformacionEstudianteDTO;
import backend.siptis.model.pjo.dto.RegistrarEstudianteDTO;

import java.util.List;

public interface InformacionUsuarioService {

    List<SiptisUser> findAll();

    InformacionEstudianteDTO registrarEstudiante(
            RegistrarEstudianteDTO estudianteDTO, SiptisUser siptisUser);

}
