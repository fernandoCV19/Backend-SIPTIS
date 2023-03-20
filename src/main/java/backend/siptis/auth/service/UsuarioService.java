package backend.siptis.auth.service;

import backend.siptis.auth.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();

    String registrarEstudiante();
}
