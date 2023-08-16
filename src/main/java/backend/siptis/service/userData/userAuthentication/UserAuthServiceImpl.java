package backend.siptis.service.userData.userAuthentication;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.auth.jwt.JWTokenUtils;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.notifications.LogInDTO;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.model.pjo.dto.usersInformationDTO.StudentInformationDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
@Transactional
@AllArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private final SiptisUserRepository siptisUserRepository;

    @Autowired
    private final AuthenticationManager authenticationManager;


    /*@Override
    public ServiceAnswer findAll() {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(siptisUserRepository.findAll()).build();

    }*/

    private ServiceAnswer registerErrorMessage(ServiceMessage serviceMessage,String errorMessage){
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(errorMessage
        ).build();
    }

    @Override
    public ServiceAnswer userInfo(Long id){
        if(!siptisUserRepository.existsById(id)){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ID_DOES_NOT_EXIST)
                    .data("No existe un usuario con el id solicitado").build();
        }
        Optional<SiptisUser> response = siptisUserRepository.findById(id);
        SiptisUser user = response.get();

        StudentInformationDTO dto = convertToStudentInformation(user);
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
            return registerErrorMessage(ServiceMessage.ERROR_INICIO_SESION_PASSWORD,message);

        }
    }

    @Override
    public Long getIdFromToken(String token){
        token = token.replace("Bearer ","");
        System.out.println("TK: "+token);
        return JWTokenUtils.getId(token);
    }

    @Override
    public Optional<SiptisUser> findByTokenPassword(String tokenPassword){
        return siptisUserRepository.findByTokenPassword(tokenPassword);
    }
    private StudentInformationDTO convertToStudentInformation(SiptisUser user){

        StudentInformationDTO student = new StudentInformationDTO();
        if(user != null){

            student.setEmail(user.getEmail());
            UserInformation information = user.getUserInformation();;
            if(information != null){
                student.setNames(information.getNames());
                student.setLastnames(information.getLastnames());
                student.setCelNumber(information.getCelNumber());
                student.setCi(information.getCi());
                student.setBirthDate(information.getBirthDate());
                student.setCodSIS(information.getCodSIS());
                Set<UserCareer> career = user.getCareer();

                for (UserCareer userCareer: career) {
                    student.setCareer(userCareer.getName());
                    student.setCareerId(userCareer.getId());
                }
            }


        }


        return student;
    }
}
