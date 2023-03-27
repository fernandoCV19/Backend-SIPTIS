package backend.siptis.service.datosUsuario;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.MensajeServicio;
import backend.siptis.commons.RespuestaServicio;
import backend.siptis.model.repository.datosUsuario.UsuarioRepository;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.repository.general.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@AllArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final UserInformationService userInformationService;




    @Override
    public RespuestaServicio findAll() {
        //return usuarioRepository.findAll();
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OK).data(usuarioRepository.findAll()).build();

    }

    @Override
    public RespuestaServicio registerStudent(StudentRegisterDTO estudianteDTO) {

        SiptisUser siptisUser = new SiptisUser();
        siptisUser.setEmail(estudianteDTO.getEmail());
        Role role = roleRepository.findById(1)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "El rol no existe"
                ));

        siptisUser.addRol(role);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String contrasena = encoder.encode(estudianteDTO.getPassword());
        siptisUser.setPassword(contrasena);
        usuarioRepository.save(siptisUser);
        //InformacionEstudianteDTO estudiante = new InformacionEstudianteDTO();

        RespuestaServicio respuesta = userInformationService
                .registerStudent(estudianteDTO, siptisUser);

        StudentInformationDTO estudiante = (StudentInformationDTO) respuesta.getData();
        estudiante.setEmail(siptisUser.getEmail());
        //return estudiante;
        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OK).data(estudiante).build();
    }
}
