package backend.siptis.auth.service;

import backend.siptis.auth.entity.User;
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
    @Autowired
    private final InformacionUsuarioService informacionUsuarioService;




    @Override
    public List<User> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public InformacionEstudianteDTO registrarEstudiante(RegistrarEstudianteDTO estudianteDTO) {

        User user = new User();
        user.setEmail(estudianteDTO.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String contrasena = encoder.encode(estudianteDTO.getContrasena());
        user.setContrasena(contrasena);
        usuarioRepository.save(user);
        //InformacionEstudianteDTO estudiante = new InformacionEstudianteDTO();

        InformacionEstudianteDTO estudiante = informacionUsuarioService
                .registrarEstudiante(estudianteDTO, user);
        estudiante.setEmail(user.getEmail());
        return estudiante;
    }
}
