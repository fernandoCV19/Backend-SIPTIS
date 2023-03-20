package backend.siptis.auth.service;

import backend.siptis.auth.entity.Usuario;
import backend.siptis.auth.repository.UsuarioRepository;
import backend.siptis.model.pjo.dto.InformacionEstudianteDTO;
import backend.siptis.model.pjo.dto.RegistrarEstudianteDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class UsuarioAuthServiceImpl implements UsuarioAuthService {

    @Autowired
    private final UsuarioRepository usuarioRepository;



    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public InformacionEstudianteDTO registrarEstudiante(RegistrarEstudianteDTO estudianteDTO) {

        Usuario usuario = new Usuario();
        usuario.setEmail(estudianteDTO.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String contrasena = encoder.encode(estudianteDTO.getContrasena());
        usuario.setContrasena(contrasena);
        usuarioRepository.save(usuario);
        InformacionEstudianteDTO estudiante = new InformacionEstudianteDTO();
        estudiante.setEmail(usuario.getEmail());
        return estudiante;
    }
}
