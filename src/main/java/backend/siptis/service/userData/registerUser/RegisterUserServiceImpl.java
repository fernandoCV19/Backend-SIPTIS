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
import backend.siptis.service.userData.checkUserInformation.CheckUserInformation;
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
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private final SiptisUserRepository siptisUserRepository;
    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final RegisterUserInformation registerUserInformation;
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
        ServiceAnswer respuesta = registerUserInformation
                .registerStudent(estudianteDTO, siptisUser);

        if(respuesta == null){
            return createResponse(
                    ServiceMessage.ERROR_REGISTRO_CUENTA, "Ocurrio un error al registrar la cuenta");
        }
        return createResponse(
                ServiceMessage.OK, "La cuenta de estudiante se registro exitosamente.");

    }

    @Override
    public ServiceAnswer registerTeacher(TeacherRegisterDTO teacherDTO) {
        ServiceAnswer validation = validateUser(teacherDTO.getEmail(), teacherDTO.getCi(), teacherDTO.getCodSIS());
        if( validation != null){
            return validation;
        }

        SiptisUser siptisUser = registerUser(teacherDTO.getEmail(), teacherDTO.getPassword(), 3);

        ServiceAnswer respuesta = registerUserInformation.registerTeacherInformation(teacherDTO, siptisUser);

        if(respuesta == null){
            return createResponse(
                    ServiceMessage.ERROR_REGISTRO_CUENTA, "Ocurrio un error al registrar la cuenta");
        }
        return createResponse(
                ServiceMessage.OK, "La cuenta de docente se registro exitosamente.");

    }

    @Override
    public ServiceAnswer registerAdmin(AdminRegisterDTO adminDTO) {

        if(siptisUserRepository.existsByEmail(adminDTO.getEmail())){
            String mensajeError = "El correo ya esta registrado en el sistema";
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR_REGISTRO_CUENTA_EMAIL).data(mensajeError).build();
        }

        SiptisUser adminUser = registerUser(adminDTO.getEmail(), adminDTO.getPassword(), 2);
        if(adminUser == null){
            return createResponse(
                    ServiceMessage.ERROR_REGISTRO_CUENTA, "Ocurrio un error al registrar la cuenta");
        }
        return createResponse(
                ServiceMessage.OK, "La cuenta de administrador se registro exitosamente.");

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
            return createResponse(ServiceMessage.ERROR_REGISTRO_CUENTA_EMAIL,errorMessage);
        }
        if(checkUserInformation.existByCi(ci)){
            String errorMessage = "El ci ya se encuentra registrado en el sistema";
            return createResponse(ServiceMessage.ERROR_REGISTRO_CUENTA_CI,errorMessage);
        }
        if(checkUserInformation.existByCodSIS(codSIS)){
            String errorMessage = "El codigo SIS ya se encuentra registrado en el sistema";
            return createResponse(ServiceMessage.ERROR_REGISTRO_CUENTA_CODSIS,errorMessage);
        }

        return null;
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage,String errorMessage){
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(errorMessage
        ).build();
    }

}
