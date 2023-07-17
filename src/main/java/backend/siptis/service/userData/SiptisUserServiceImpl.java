package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.auth.jwt.JWTokenUtils;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.records.Activity;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.*;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.StudentInformationDTO;
import backend.siptis.model.pjo.vo.userData.TribunalInfoToAssignSection;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SiptisUserServiceImpl implements SiptisUserService {

    private final SiptisUserRepository usuarioCommonRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public ServiceAnswer findAll() {
        List<SiptisUser> userDBList = usuarioCommonRepository.findAll();
        List<UserGeneralInformationDTO> userList = new ArrayList<>();
        for (SiptisUser user : userDBList) {
            UserGeneralInformationDTO userDTO = convertToDTO(user);

            userList.add(userDTO);

        }
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(userList).build();
    }

    @Override
    public ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id) {
        return null;
    }

    @Override
    public ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Long id) {
        Optional<SiptisUser> usuarioOptional = usuarioCommonRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }

        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioCommonRepository.existsByEmail(email);
    }

    @Override
    public SiptisUser findByEmail(String email) {

        Optional<SiptisUser> user = usuarioCommonRepository.findByEmail(email);
        return user.get();
    }


    @Override
    public boolean existsTokenPassword(String tokenPassword) {

        return usuarioCommonRepository.existsByTokenPassword(tokenPassword);
    }

    @Override
    public SiptisUser findById(long id) {
        return usuarioCommonRepository.findById(id).get();
    }

    @Override
    public ServiceAnswer getAllUsers() {
        List<SiptisUser> userList = usuarioCommonRepository.findAll();
        return userList.isEmpty() ?
                ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(userList).build() :
                ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(usuarioCommonRepository.findAll()).build();
    }

    @Override
    public SiptisUser save(SiptisUser siptisUser) {
        return usuarioCommonRepository.save(siptisUser);
    }

    @Override
    public ServiceAnswer getPossibleTribunals() {
        List<SiptisUser> query = usuarioCommonRepository.findByRolesName("TRIBUNAL");
        List<TribunalInfoToAssignSection> res = query.stream().map(TribunalInfoToAssignSection::new).toList();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(res).build();
    }

    @Override
    public ServiceAnswer getPersonalActivities(Long id, Pageable pageable) {
        Page<Activity> activities = usuarioCommonRepository.findAllPersonalActivities(id, pageable);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(activities).build();
    }

    private ServiceAnswer registerErrorMessage(ServiceMessage serviceMessage,String errorMessage){
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(errorMessage
        ).build();
    }



    @Override
    public ServiceAnswer logIn(LogInDTO logInDTO){

        if(!usuarioCommonRepository.existsByEmail(logInDTO.getEmail())){
            String errorMessage = "El correo al que intenta acceder no se encuentra registrado en el sistema";
            return registerErrorMessage(ServiceMessage.ERROR_INICIO_SESION_EMAIL,errorMessage);
        }

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logInDTO.getEmail(),logInDTO.getPassword())
            );

            if (auth.isAuthenticated()){
                UserInformationService.UserDetailImp userDetails= (UserInformationService.UserDetailImp) auth.getPrincipal();
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
        return usuarioCommonRepository.findByTokenPassword(tokenPassword);
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


    private UserGeneralInformationDTO convertToDTO(SiptisUser user) {
        UserGeneralInformationDTO userDTO = new UserGeneralInformationDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}

