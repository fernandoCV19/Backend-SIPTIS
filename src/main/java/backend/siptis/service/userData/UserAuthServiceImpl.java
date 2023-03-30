package backend.siptis.service.userData;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.repository.general.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
@AllArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private final SiptisUserRepository siptisUserRepository;
    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final UserInformationService userInformationService;




    @Override
    public ServiceAnswer findAll() {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(siptisUserRepository.findAll()).build();

    }

    @Override
    public ServiceAnswer registerStudent(StudentRegisterDTO estudianteDTO) {

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
        siptisUserRepository.save(siptisUser);
        //InformacionEstudianteDTO estudiante = new InformacionEstudianteDTO();
        ServiceAnswer respuesta = userInformationService
                .registerStudent(estudianteDTO, siptisUser);

        StudentInformationDTO estudiante = (StudentInformationDTO) respuesta.getData();
        estudiante.setEmail(siptisUser.getEmail());

        //return estudiante;
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(estudiante).build();
    }

    @Override
    public ServiceAnswer registerAdmin(AdminRegisterDTO adminDTO) {
        SiptisUser adminUser = new SiptisUser();
        adminUser.setEmail(adminDTO.getEmail());
        Role role = roleRepository.findById(2)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "El rol no existe"
                ));

        adminUser.addRol(role);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String contrasena = encoder.encode(adminDTO.getPassword());
        adminUser.setPassword(contrasena);
        siptisUserRepository.save(adminUser);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(adminUser).build();
    }
}
