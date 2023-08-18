package backend.siptis.service.userData;

import backend.siptis.auth.entity.RefreshToken;
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
import backend.siptis.model.pjo.dto.authentication.TokenDTO;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterUserDTO;
import backend.siptis.model.pjo.vo.userData.TribunalInfoToAssignSection;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.service.generalInformation.RoleService;
import backend.siptis.service.generalInformation.UserAreaService;
import backend.siptis.service.generalInformation.UserCareerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class SiptisUserServiceImpl implements SiptisUserService {

    private final SiptisUserRepository siptisUserRepository;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final RoleService roleService;
    private final UserCareerService userCareerService;
    private final UserInformationService userInformationService;
    private final UserAreaService userAreaService;


    private boolean existsUserByEmail(String email) {
        return siptisUserRepository.existsByEmail(email);
    }
    private boolean existsUserById(long id) { return siptisUserRepository.existsById(id);}
    private SiptisUser findUserById(long id) { return siptisUserRepository.findById(id).get();}

    private TokenDTO createTokenDTO(UserInformationService.UserDetailImp userDetails){
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails);
        String token = JWTokenUtils.createToken(userDetails);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        tokenDTO.setRefreshToken(refreshToken.getToken());
        return tokenDTO;
    }

    @Override
    public ServiceAnswer logIn(LogInDTO logInDTO){

        if(!existsUserByEmail(logInDTO.getEmail())){
            String errorMessage = "Credenciales incorrectas.";
            return createResponse(ServiceMessage.ERROR_BAD_CREDENTIALS,errorMessage);
        }

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logInDTO.getEmail(),logInDTO.getPassword())
            );

            if (auth.isAuthenticated()){
                UserInformationService.UserDetailImp userDetails= (UserInformationService.UserDetailImp) auth.getPrincipal();
                TokenDTO tokenDTO = createTokenDTO(userDetails);

                return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(tokenDTO).build();

            } else {
                return createResponse(ServiceMessage.ERROR_LOG_IN,"Ocurrió un error al iniciar Sesión.");
            }
        }catch (Exception e){
            System.out.println("Error de autenticación: "+e.getMessage());
            return createResponse(ServiceMessage.ERROR_BAD_CREDENTIALS,"Credenciales incorrectas.");
        }
    }

    @Override
    public ServiceAnswer getAllUsers() {
        List<SiptisUser> userList = siptisUserRepository.findAll();
        return userList.isEmpty() ?
                ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(userList).build() :
                ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(siptisUserRepository.findAll()).build();
    }

    @Override
    public ServiceAnswer getUserById(Long id) {
        SiptisUser user = siptisUserRepository.findById(id).get();
        System.out.println(user.getEmail());
        return createResponse
                (ServiceMessage.OK, user.getEmail());

    }

    @Override
    public ServiceAnswer getUserByEmail(String email) {
        SiptisUser user = siptisUserRepository.findOneByEmail(email).get();
        System.out.println(user.getEmail());
        return createResponse
                (ServiceMessage.OK, user.getEmail());

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
    public ServiceAnswer registerTeacher(RegisterUserDTO dto) {

        ServiceAnswer answer = registerRoleAndInformation(dto, "TEACHER");
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
    public ServiceAnswer updateToken(String refreshToken) {
        RefreshToken token = refreshTokenService.findByToken(refreshToken);
        if(token == null){
            return createResponse(ServiceMessage.ERROR,"El token no existe");
        }
        token = refreshTokenService.verifyExpirationDate(token);
        if(token == null){
            return createResponse(ServiceMessage.ERROR,"El token ya expiro");
        }

        SiptisUser user = token.getSiptisUser();
        //Long id = refreshTokenService.getTokenUser(refreshToken);
        //SiptisUser user = siptisUserRepository.findOneById(id).get();
        String updatedToken = JWTokenUtils.createToken(user);
        RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(updatedToken);
        tokenDTO.setRefreshToken(newRefreshToken.getToken());

        return createResponse(ServiceMessage.OK, tokenDTO);
    }
    @Override
    public Long getIdFromToken(String token){
        token = token.replace("Bearer ","");
        return JWTokenUtils.getId(token);
    }

    @Override
    public ServiceAnswer getStudentList(String search,Pageable pageable){
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = (pageNumber - 1) * pageSize;
        return createResponse(ServiceMessage.OK,
                siptisUserRepository.searchUserList(search, "STUDENT", pageable));
                //siptisUserRepository.searchUser(search));
    }

    @Override
    public ServiceAnswer getTeacherList(String search,Pageable pageable){
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = (pageNumber - 1) * pageSize;
        return createResponse(ServiceMessage.OK,
                siptisUserRepository.searchUserList(search, "TEACHER", pageable));
        //siptisUserRepository.searchUser(search));
    }

    @Override
    public ServiceAnswer getAdminList(Pageable pageable){
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = (pageNumber - 1) * pageSize;
        return createResponse(ServiceMessage.OK,
                siptisUserRepository.searchAdminList( "ADMIN", pageable));
        //siptisUserRepository.searchUser(search));
    }

    @Override
    public ServiceAnswer deleteUser(Long id) {
        if(!siptisUserRepository.existsById(id)){
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, "No pudimos encontrar al usuario");
        }
        SiptisUser user = siptisUserRepository.findById(id).get();
        ServiceAnswer answer = deleteValidations(user);
        if(answer != null){
            return answer;
        }
        siptisUserRepository.deleteById(id);
        return createResponse(ServiceMessage.USER_DELETED, "El usuario fue eliminado");
    }

    private ServiceAnswer deleteValidations(SiptisUser user){
        if(user.getStudents().size() > 0){
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, tiene un proyecto en curso.");
        }
        if(user.getAvailableSchedules().size() > 0){
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, tiene horarios en curso.");
        }
        if(user.getDocuments().size() > 0){
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, tiene documentos asociados.");
        }
        if(user.getReviews().size() > 0){
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, es revisor de proyectos activo.");
        }
        if(user.getSupervisorOf().size() > 0){
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, es supervisor de proyectos activo.");
        }
        if(user.getTeacherOf().size() > 0){
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, es docente de proyectos activo.");
        }
        if(user.getTutorOf().size() > 0){
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, es tutol de proyectos activo.");
        }

        if(user.getTribunalOf().size() > 0){
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, es tribunal de proyectos activo.");
        }

        return null;
    }

    @Override
    public ServiceAnswer getUserPersonalInformation(Long id) {
        if(!existsUserById(id)){
            return createResponse(ServiceMessage.NOT_FOUND,
                    "El usuario solicitado no se encuentra en el sistema.");
        }
        SiptisUser user = findUserById(id);

        return userInformationService.getUserPersonalInformation(user);
    }

    @Override
    public ServiceAnswer getTeacherAreasById(Long id) {
        if(!existsUserById(id)){
            return createResponse(ServiceMessage.NOT_FOUND,
                    "El usuario solicitado no se encuentra en el sistema.");
        }
        SiptisUser user = siptisUserRepository.findById(id).get();
        Set<UserArea> areas = user.getAreas();
        if(areas == null){
            return createResponse(ServiceMessage.NOT_FOUND,
                    "No se pudo obtener las areas del usuario solicitado.");
        }
        return createResponse(ServiceMessage.OK, areas);
    }

    @Override
    public ServiceAnswer getTeacherNotSelectedAreasById(Long id) {
        if(!existsUserById(id)){
            return createResponse(ServiceMessage.NOT_FOUND,
                    "El usuario solicitado no se encuentra en el sistema.");
        }
        return createResponse(ServiceMessage.OK, siptisUserRepository.getNotSelectedAreas(id));
    }

    @Override
    public ServiceAnswer updateAreas(Long id, UserSelectedAreasDTO dto) {
        if(!existsUserById(id)){
            return createResponse(ServiceMessage.NOT_FOUND,
                    "El usuario solicitado no se encuentra en el sistema.");
        }
        List<Long> ids = dto.getIds();
        Set<UserArea> areas = new HashSet<>();
        for (Long areaId: ids) {
            if(!userAreaService.userAreaExistById(areaId.intValue())){
                return createResponse(ServiceMessage.NOT_FOUND,
                        "El area solicitada no se encuentra en el sistema.");
            }
            areas.add(userAreaService.getUserAreaById(areaId.intValue()));
        }
        SiptisUser user = findUserById(id);
        user.setAreas(areas);
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, "Se registraron las areas correctamente.");
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
    public ServiceAnswer userEditPersonalInformation() {
        return null;
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

        answer = userInformationService.registerUserInformation(dto, user);
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

    private ServiceAnswer createResponse(ServiceMessage serviceMessage,Object data){
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }



    /*
    private final SiptisUserRepository siptisUserRepository;
    private final RoleService roleService;
    private final UserCareerService userCareerService;
    private final UserInformationService userInformationService;
    private final RefreshTokenService refreshTokenService;


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
    public SiptisUser save(SiptisUser siptisUser) {
        return siptisUserRepository.save(siptisUser);
    }

    @Override
    public ServiceAnswer getPossibleTribunals() {
        List<SiptisUser> query = siptisUserRepository.findByRolesName("TRIBUNAL");
        List<TribunalInfoToAssignSection> res = query.stream().map(TribunalInfoToAssignSection::new).toList();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(res).build();
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
        //UserInformation information = user.getUserInformation();

        //return createResponse(ServiceMessage.OK, information);
        return userInformationService.getUserPersonalInformation(user);
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


    @Override
    public Optional<SiptisUser> findByTokenPassword(String tokenPassword){
        return siptisUserRepository.findByTokenPassword(tokenPassword);
    }
*/
}

