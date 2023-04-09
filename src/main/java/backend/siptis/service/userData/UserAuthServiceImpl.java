package backend.siptis.service.userData;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.auth.jwt.JWTokenUtils;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.model.repository.general.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


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
    @Autowired
    private final AuthenticationManager authenticationManager;


    @Override
    public ServiceAnswer findAll() {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(siptisUserRepository.findAll()).build();

    }

    @Override
    public ServiceAnswer registerStudent(StudentRegisterDTO estudianteDTO) {

        if(siptisUserRepository.existsByEmail(estudianteDTO.getEmail())){
            String errorMessage = "El correo ya se encuentra registrado en el sistema";
            return registerErrorMessage(ServiceMessage.ERROR_REGISTRO_CUENTA_EMAIL,errorMessage);
        }
        if(userInformationService.existByCi(estudianteDTO.getCi())){
            String errorMessage = "El ci ya se encuentra registrado en el sistema";
            return registerErrorMessage(ServiceMessage.ERROR_REGISTRO_CUENTA_CI,errorMessage);
        }
        if(userInformationService.existByCodSIS(estudianteDTO.getCodSIS())){
            String errorMessage = "El codigo SIS ya se encuentra registrado en el sistema";
            return registerErrorMessage(ServiceMessage.ERROR_REGISTRO_CUENTA_CODSIS,errorMessage);
        }


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

    private ServiceAnswer registerErrorMessage(ServiceMessage serviceMessage,String errorMessage){
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(errorMessage
        ).build();
    }

    @Override
    public ServiceAnswer registerAdmin(AdminRegisterDTO adminDTO) {

        if(siptisUserRepository.existsByEmail(adminDTO.getEmail())){
            String mensajeError = "El correo ya esta registrado en el sistema";
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR_REGISTRO_CUENTA).data(mensajeError).build();
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

    @Override
    public ServiceAnswer userInfo(Long id){
        Optional<SiptisUser> response = siptisUserRepository.findById(id);
        SiptisUser user = response.get();
        UserInformation information = user.getUserInformation();
        StudentInformationDTO dto = convertToStudentInformation(user, information);
        //System.out.println(response.get());
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(dto).build();
    }

    @Override
    public ServiceAnswer logIn(LogInDTO logInDTO){

        if(!siptisUserRepository.existsByEmail(logInDTO.getEmail())){
            String errorMessage = "El correo al que intenta acceder no se encuentra registrado en el sistema";
            return registerErrorMessage(ServiceMessage.ERROR_INICIO_SESION_EMAIL,errorMessage);
        }

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logInDTO.getEmail(),logInDTO.getPassword())
            );

            if (auth.isAuthenticated()){
                UserDetailImp userDetails= (UserDetailImp) auth.getPrincipal();
                String token = JWTokenUtils.createToken(userDetails);

                return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(token).build();

            } else {
                String message = "Ocurrió un error al iniciar Sesión.";
                return registerErrorMessage(ServiceMessage.ERROR_INICIO_SESION,message);
            }
        }catch (Exception e){
            System.out.println("Error de autenticación: "+e.getMessage());
            String message = "Contraseña incorrecta.";
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR_INICIO_SESION_PASSWORD).data(message).build();

        }
    }

    private StudentInformationDTO convertToStudentInformation(SiptisUser user, UserInformation information){

        StudentInformationDTO student = new StudentInformationDTO();

        student.setNames(information.getNames());
        student.setLastnames(information.getLastnames());
        student.setEmail(user.getEmail());
        student.setCelNumber(information.getCelNumber());
        student.setCi(information.getCi());
        student.setBirthDate(information.getBirthDate());
        student.setCodSIS(information.getCodSIS());

        Set<UserCareer> career = user.getCareer();

        for (UserCareer userCareer: career) {
            student.setCareer(userCareer.getName());
            student.setCareerId(userCareer.getId());
        }
        return student;
    }
}
