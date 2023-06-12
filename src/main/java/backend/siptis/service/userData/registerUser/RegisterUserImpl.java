package backend.siptis.service.userData.registerUser;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.pjo.dto.TeacherRegisterDTO;
import backend.siptis.model.repository.general.RoleRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.model.repository.userData.UserInformationRepository;
import backend.siptis.service.userData.UserInformationService;
import backend.siptis.service.userData.checkUserInformation.CheckUserInformation;
import backend.siptis.service.userData.checkUserInformation.CheckUserInformationImpl;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class RegisterUserImpl implements RegisterUser{

    @Autowired
    private final SiptisUserRepository siptisUserRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final RegisterUserInformation userInformationService;
    @Autowired
    private  final CheckUserInformation checkUserInformation;
    @Autowired
    private final AuthenticationManager authenticationManager;

    @Override
    public ServiceAnswer registerStudent(StudentRegisterDTO estudianteDTO) {

        ServiceAnswer validation = validateUser(estudianteDTO.getEmail(), estudianteDTO.getCi(), estudianteDTO.getCodSIS());

        if( validation != null){
            return validation;
        }


        SiptisUser siptisUser = registerUser(estudianteDTO.getEmail(), estudianteDTO.getPassword(), 1);
        //InformacionEstudianteDTO estudiante = new InformacionEstudianteDTO();
        ServiceAnswer respuesta = userInformationService
                .registerStudent(estudianteDTO, siptisUser);

        StudentInformationDTO estudiante = (StudentInformationDTO) respuesta.getData();
        estudiante.setEmail(siptisUser.getEmail());

        //return estudiante;
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(estudiante).build();
    }

    @Override
    public ServiceAnswer registerTeacher(TeacherRegisterDTO teacherDTO) {
        ServiceAnswer validation = validateUser(teacherDTO.getEmail(), teacherDTO.getCi(), teacherDTO.getCodSIS());

        if( validation != null){
            return validation;
        }

        SiptisUser siptisUser = registerUser(teacherDTO.getEmail(), teacherDTO.getPassword(), 3);

        ServiceAnswer respuesta = userInformationService.registerTeacher(teacherDTO, siptisUser);

        StudentInformationDTO estudiante = (StudentInformationDTO) respuesta.getData();
        estudiante.setEmail(siptisUser.getEmail());

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(estudiante).build();

    }

    private SiptisUser registerUser(String email, String password, int roleId){
        SiptisUser siptisUser = new SiptisUser();
        siptisUser.setEmail(email);
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "El rol no existe"
                ));
        siptisUser.addRol(role);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String contrasena = encoder.encode(password);
        siptisUser.setPassword(contrasena);
        return siptisUserRepository.save(siptisUser);
    }

    private ServiceAnswer validateUser(String email, String ci, String codSIS){
        if(siptisUserRepository.existsByEmail(email)){
            String errorMessage = "El correo ya se encuentra registrado en el sistema";
            return registerErrorMessage(ServiceMessage.ERROR_REGISTRO_CUENTA_EMAIL,errorMessage);
        }
        if(checkUserInformation.existByCi(ci)){
            String errorMessage = "El ci ya se encuentra registrado en el sistema";
            return registerErrorMessage(ServiceMessage.ERROR_REGISTRO_CUENTA_CI,errorMessage);
        }
        if(checkUserInformation.existByCodSIS(codSIS)){
            String errorMessage = "El codigo SIS ya se encuentra registrado en el sistema";
            return registerErrorMessage(ServiceMessage.ERROR_REGISTRO_CUENTA_CODSIS,errorMessage);
        }

        return null;
    }

    private ServiceAnswer registerErrorMessage(ServiceMessage serviceMessage,String errorMessage){
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(errorMessage
        ).build();
    }

    @Override
    public ServiceAnswer registerAdmin(AdminRegisterDTO adminDTO) {

        if(siptisUserRepository.existsByEmail(adminDTO.getEmail())){
            String mensajeError = "El correo ya esta registrado en el sistema";
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR_REGISTRO_CUENTA_EMAIL).data(mensajeError).build();
        }

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
