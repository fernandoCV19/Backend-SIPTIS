package backend.siptis.auth.service;

import backend.siptis.auth.entity.SiptisUser;
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
    public List<SiptisUser> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public InformacionEstudianteDTO registrarEstudiante(RegistrarEstudianteDTO estudianteDTO) {

        SiptisUser siptisUser = new SiptisUser();
        siptisUser.setEmail(estudianteDTO.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String contrasena = encoder.encode(estudianteDTO.getContrasena());
        siptisUser.setPassword(contrasena);
        usuarioRepository.save(siptisUser);
        //InformacionEstudianteDTO estudiante = new InformacionEstudianteDTO();

        InformacionEstudianteDTO estudiante = informacionUsuarioService
                .registrarEstudiante(estudianteDTO, siptisUser);
        estudiante.setEmail(siptisUser.getEmail());
        return estudiante;
    }
}
