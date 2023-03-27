package backend.siptis.auth.service;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.repository.datosUsuario.UsuarioRepository;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
@AllArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final UserInformationService userInformationService;




    @Override
    public List<SiptisUser> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public StudentInformationDTO registerStudent(StudentRegisterDTO estudianteDTO) {

        SiptisUser siptisUser = new SiptisUser();
        siptisUser.setEmail(estudianteDTO.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String contrasena = encoder.encode(estudianteDTO.getPassword());
        siptisUser.setPassword(contrasena);
        usuarioRepository.save(siptisUser);
        //InformacionEstudianteDTO estudiante = new InformacionEstudianteDTO();

        StudentInformationDTO estudiante = userInformationService
                .registerStudent(estudianteDTO, siptisUser);
        estudiante.setEmail(siptisUser.getEmail());
        return estudiante;
    }
}
