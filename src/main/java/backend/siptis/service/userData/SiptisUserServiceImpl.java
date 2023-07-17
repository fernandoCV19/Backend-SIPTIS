package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.auth.jwt.JWTokenUtils;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.records.Activity;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.pjo.dto.*;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import backend.siptis.model.pjo.vo.userData.TribunalInfoToAssignSection;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SiptisUserServiceImpl implements SiptisUserService {

    private final SiptisUserRepository usuarioCommonRepository;
    private final AuthenticationManager authenticationManager;
    private final UserInformationService userInformationService;


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
    public ServiceAnswer existsById(int id) {
        Long longId = Long.valueOf(id);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(usuarioCommonRepository.existsById(longId)).build();
    }

    private boolean existsUserById(long id) {
        return usuarioCommonRepository.existsById(id);
    }
    private boolean existsUserByEmail(String email) {
        return usuarioCommonRepository.existsByEmail(email);
    }
    private SiptisUser findUserByEmail(String email) {
        return usuarioCommonRepository.findByEmail(email).get();
    }
    private SiptisUser findUserById(long id) {
        return usuarioCommonRepository.findById(id).get();
    }

    @Override
    public ServiceAnswer existsByEmail(String email) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(existsByEmail(email)).build();
    }

    @Override
    public ServiceAnswer findByEmail(String email) {

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(findUserByEmail(email)).build();
    }

    @Override
    public ServiceAnswer existsTokenPassword(String tokenPassword) {

        boolean response = usuarioCommonRepository.existsByTokenPassword(tokenPassword);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(response).build();
    }

    @Override
    public ServiceAnswer findById(long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(findUserById(id)).build();
    }

    @Override
    public ServiceAnswer getAllUsers() {
        List<SiptisUser> userList = usuarioCommonRepository.findAll();
        return userList.isEmpty() ?
                ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(userList).build() :
                ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(usuarioCommonRepository.findAll()).build();
    }

    @Override
    public ServiceAnswer getStudentCareerById(Long id) {
        if(!existsUserById(id)){
            String errorMessage = "El usuario al que intenta acceder no se encuentra registrado en el sistema";
            return createResponse(ServiceMessage.ERROR,errorMessage);
        }
        SiptisUser user = findUserById(id);
        Set<UserCareer> career = user.getCareer();
        return  createResponse(ServiceMessage.OK, career);

    }

    @Override
    public ServiceAnswer getTeacherAreasById(Long id) {
        if(!existsUserById(id)){
            String errorMessage = "El usuario al que intenta acceder no se encuentra registrado en el sistema";
            return createResponse(ServiceMessage.ERROR,errorMessage);
        }
        SiptisUser user = findUserById(id);
        Set<UserArea> areas = user.getAreas();
        return createResponse(ServiceMessage.OK, areas);
    }

    @Override
    public ServiceAnswer getTeacherNotSelectedAreasById(Long id) {
        return userInformationService.getTeacherNotSelectedAreasById(id);
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

    private ServiceAnswer createResponse(ServiceMessage serviceMessage,Object data){
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }



    @Override
    public ServiceAnswer logIn(LogInDTO logInDTO){

        if(!existsUserByEmail(logInDTO.getEmail())){
            String errorMessage = "El correo al que intenta acceder no se encuentra registrado en el sistema";
            return createResponse(ServiceMessage.ERROR_INICIO_SESION_EMAIL,errorMessage);
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
                return createResponse(ServiceMessage.ERROR_INICIO_SESION,message);
            }
        }catch (Exception e){
            System.out.println("Error de autenticación: "+e.getMessage());
            String message = "Contraseña incorrecta.";
            return createResponse(ServiceMessage.ERROR_INICIO_SESION_PASSWORD,message);

        }
    }

    @Override
    public Long getIdFromToken(String token){
        token = token.replace("Bearer ","");
        System.out.println("TK: "+token);
        return JWTokenUtils.getId(token);
    }

    @Override
    public ServiceAnswer getUserPersonalInformation(long id) {
        if(!existsUserById(id)){
            String errorMessage = "El usuario al que intenta acceder no se encuentra registrado en el sistema";
            return createResponse(ServiceMessage.ERROR,errorMessage);
        }

        SiptisUser user = findUserById(id);
        return userInformationService.getUserPersonalInformation(user);

    }

    @Override
    public Optional<SiptisUser> findByTokenPassword(String tokenPassword){
        return usuarioCommonRepository.findByTokenPassword(tokenPassword);
    }

    private UserGeneralInformationDTO convertToDTO(SiptisUser user) {
        UserGeneralInformationDTO userDTO = new UserGeneralInformationDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

}

