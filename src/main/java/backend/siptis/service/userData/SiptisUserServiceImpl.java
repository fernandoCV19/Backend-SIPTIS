package backend.siptis.service.userData;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.auth.jwt.JWTokenUtils;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.records.Activity;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.*;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterUserDTO;
import backend.siptis.model.pjo.vo.userData.TribunalInfoToAssignSection;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.service.generalInformation.RoleService;
import backend.siptis.service.generalInformation.UserCareerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SiptisUserServiceImpl implements SiptisUserService {

    private final SiptisUserRepository siptisUserRepository;
    private final RoleService roleService;
    private final UserCareerService userCareerService;
    private final AuthenticationManager authenticationManager;
    private final UserInformationService userInformationService;


    @Override
    public ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Integer id) {
        return null;
    }

    @Override
    public ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Long id) {
        Optional<SiptisUser> usuarioOptional = siptisUserRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }

        return null;
    }

    @Override
    public ServiceAnswer existsById(int id) {
        Long longId = Long.valueOf(id);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(siptisUserRepository.existsById(longId)).build();
    }

    private boolean existsUserById(long id) {
        return siptisUserRepository.existsById(id);
    }
    private boolean existsUserByEmail(String email) {
        return siptisUserRepository.existsByEmail(email);
    }
    private SiptisUser findUserByEmail(String email) {
        return siptisUserRepository.findByEmail(email).get();
    }
    private SiptisUser findUserById(long id) {
        return siptisUserRepository.findById(id).get();
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

        boolean response = siptisUserRepository.existsByTokenPassword(tokenPassword);
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
        List<SiptisUser> userList = siptisUserRepository.findAll();
        return userList.isEmpty() ?
                ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(userList).build() :
                ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(siptisUserRepository.findAll()).build();
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
        return siptisUserRepository.save(siptisUser);
    }

    @Override
    public ServiceAnswer getPossibleTribunals() {
        List<SiptisUser> query = siptisUserRepository.findByRolesName("TRIBUNAL");
        List<TribunalInfoToAssignSection> res = query.stream().map(TribunalInfoToAssignSection::new).toList();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(res).build();
    }

    private ServiceAnswer validateEmail(String email){
        if(email == null || email == ""){
            return createResponse(ServiceMessage.ERROR_REGISTER_ACCOUNT_EMAIL, "Tiene que ingresar un correo.");
        }
        if(!Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches()){
            return createResponse(ServiceMessage.ERROR_REGISTER_ACCOUNT_EMAIL, "El correo no tiene el formato correcto.");
        }
        if(existsUserByEmail(email)){
            return createResponse(ServiceMessage.ERROR_REGISTER_ACCOUNT_EMAIL, "El correo ya se encuentra registrado.");
        }
        return null;
    }

    private ServiceAnswer validatePassword(String password){

        if(password == null || password == ""){
            return createResponse(ServiceMessage.ERROR_REGISTER_ACCOUNT_PASSWORD, "Tiene que ingresar una contraseña.");
        }
        if(password.length() < 6 ){
            return createResponse(ServiceMessage.ERROR_REGISTER_ACCOUNT_PASSWORD, "La contraseña es muy corta.");
        }
        return null;
    }

    private ServiceAnswer registerUser(String email, String password) {
        ServiceAnswer answer = validateEmail(email);
        if(answer != null){
            return answer;
        }
        answer = validatePassword(password);
        if(answer != null){
            return answer;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String userPassword = encoder.encode(password);
        return createResponse(ServiceMessage.OK, new SiptisUser(email, userPassword));
    }

    private ServiceAnswer registerRoleAndInformation(RegisterUserDTO dto, String career){
        ServiceAnswer answer = registerUser(dto.getEmail(), dto.getPassword());
        SiptisUser user;

        if(answer.getServiceMessage() == ServiceMessage.OK){
            user = (SiptisUser) answer.getData();
        }else{
            return  answer;
        }

        answer = userInformationService.registerUserInformation(dto);
        if(answer.getServiceMessage() == ServiceMessage.OK){
            UserInformation info = (UserInformation) answer.getData();
            user.setUserInformation(info);
        }else{
            return  answer;
        }

        answer = roleService.getRoleByName(career);
        if(answer.getServiceMessage() == ServiceMessage.OK){
            Role role = (Role) answer.getData();
            user.addRol(role);
        }else{
            return  answer;
        }

        return createResponse(ServiceMessage.OK, user);
    }


@Override
public ServiceAnswer registerStudent(RegisterStudentDTO dto) {


    ServiceAnswer answer = registerRoleAndInformation(dto, "STUDENT");
    SiptisUser user;

    if(answer.getServiceMessage() == ServiceMessage.OK){
        user = (SiptisUser) answer.getData();
    }else{
        return  answer;
    }

    answer = userCareerService.getCareerByName(dto.getCareer());
    if(answer.getServiceMessage() == ServiceMessage.OK){
        UserCareer career = (UserCareer) answer.getData();
        user.addCareer(career);
    }else{
        return  answer;
    }
    siptisUserRepository.save(user);
    return createResponse
            (ServiceMessage.OK, "El estudiante fue registrado exitosamente");

}

    @Override
    public ServiceAnswer registerTeacher(RegisterUserDTO dto) {

        ServiceAnswer answer = registerRoleAndInformation(dto, "STUDENT");
        SiptisUser user;
        if(answer.getServiceMessage() == ServiceMessage.OK){
            user = (SiptisUser) answer.getData();
        }else{
            return  answer;
        }

        siptisUserRepository.save(user);
        return createResponse
                (ServiceMessage.OK, "El docente fue registrado exitosamente");
    }

    @Override
    public ServiceAnswer registerAdmin(AdminRegisterDTO dto) {

        ServiceAnswer answer = registerUser(dto.getEmail(), dto.getPassword());
        SiptisUser user;
        if(answer.getServiceMessage() == ServiceMessage.OK){
            user = (SiptisUser) answer.getData();
        }else{
            return  answer;
        }

        answer = roleService.getRoleByName("ADMIN");
        if(answer.getServiceMessage() == ServiceMessage.OK){
            Role role = (Role) answer.getData();
            user.addRol(role);
        }else{
            return  answer;
        }

        siptisUserRepository.save(user);
        return createResponse
                (ServiceMessage.OK, "El administrador fue registrado exitosamente");

    }

    @Override
    public ServiceAnswer userEditPersonalInformation() {
        return null;
    }

    @Override
    public ServiceAnswer adminEditUserPersonalInformation() {
        return null;
    }

    @Override
    public ServiceAnswer getUserPersonalInformation(Long id) {
        if(!existsUserById(id)){
            return createResponse(ServiceMessage.OK, "No se pudo encontrar al usuario");
        }
        SiptisUser user = siptisUserRepository.findOneById(id).get();
        UserInformation information = user.getUserInformation();

        return createResponse(ServiceMessage.OK, information);
    }

    @Override
    public ServiceAnswer getUserStudentPersonalInformation(Long id) {
        return null;
    }

    @Override
    public ServiceAnswer getPersonalActivities(Long id, Pageable pageable) {
        Page<Activity> activities = siptisUserRepository.findAllPersonalActivities(id, pageable);
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
        return siptisUserRepository.findByTokenPassword(tokenPassword);
    }

}

