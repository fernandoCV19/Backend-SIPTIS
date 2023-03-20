package backend.siptis.auth.service;

import backend.siptis.auth.entity.Usuario;
import backend.siptis.auth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public String registrarEstudiante() {
        return null;
    }
}
