package backend.siptis.service.userData;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.auth.jwt.JWTokenUtils;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.*;
import backend.siptis.model.pjo.dto.authentication.TokenDTO;
import backend.siptis.model.pjo.dto.notifications.LogInDTO;
import backend.siptis.model.pjo.dto.userDataDTO.*;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterUserDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.*;
import backend.siptis.model.pjo.vo.userData.TribunalInfoToAssignSection;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
import backend.siptis.model.repository.userData.RoleRepository;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SiptisUserServiceImpl implements SiptisUserService{

    private final SiptisUserRepository siptisUserRepository;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final UserCareerService userCareerService;
    private final UserInformationService userInformationService;
    private final UserAreaService userAreaService;
    private final ProjectRepository projectRepository;


    @Override
    public ServiceAnswer getPossibleTribunals() {
        List<SiptisUser> query = siptisUserRepository.findByRolesName("TRIBUNAL");
        List<TribunalInfoToAssignSection> res = query.stream().map(TribunalInfoToAssignSection::new).toList();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(res).build();
    }

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
                    new UsernamePasswordAuthenticationToken(logInDTO.getEmail(),logInDTO.getPassword())
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
    public ServiceAnswer registerAdmin(RegisterAdminDTO dto) {
        ServiceAnswer answer = registerUser(dto.getEmail(), dto.getPassword());
        SiptisUser user;
        if(answer.getServiceMessage() == ServiceMessage.OK){
            user = (SiptisUser) answer.getData();
        }else{return  answer;}

        answer = roleService.getRoleByName("ADMIN");
        if(answer.getServiceMessage() == ServiceMessage.OK){
            Role role = (Role) answer.getData();
            user.addRol(role);
        }else{return  answer;}

        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.SUCCESSFUL_USER_REGISTER, "El administrador fue registrado exitosamente");
    }


    @Override
    public ServiceAnswer registerStudent(RegisterStudentDTO dto) {
        ServiceAnswer answer = registerUser(dto.getEmail(), dto.getCi());
        SiptisUser user;
        if(answer.getServiceMessage() == ServiceMessage.OK){
            user = (SiptisUser) answer.getData();
        }else{return  answer;}

        answer = userInformationService.registerUserInformation(dto);
        if(answer.getServiceMessage() == ServiceMessage.OK){
            UserInformation information = (UserInformation) answer.getData();
            user.setUserInformation(information);
            information.setSiptisUser(user);
        }else{return  answer;}

        answer = roleService.getRoleByName("STUDENT");
        if(answer.getServiceMessage() == ServiceMessage.OK){
            Role role = (Role) answer.getData();
            user.addRol(role);
        }else{return  answer;}
        answer = userCareerService.getCareerByName(dto.getCareer());
        if(answer.getServiceMessage() == ServiceMessage.OK){
            UserCareer career = (UserCareer) answer.getData();
            user.addCareer(career);
        }else{
            return  answer;
        }
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.SUCCESSFUL_USER_REGISTER, "El estudiante fue registrado exitosamente");

    }

    @Override
    public ServiceAnswer registerUser(RegisterUserDTO dto) {
        ServiceAnswer answer = registerUser(dto.getEmail(), dto.getCi());
        SiptisUser user;
        if(answer.getServiceMessage() == ServiceMessage.OK){
            user = (SiptisUser) answer.getData();
        }else{return  answer;}

        answer = userInformationService.registerUserInformation(dto);
        if(answer.getServiceMessage() == ServiceMessage.OK){
            UserInformation information = (UserInformation) answer.getData();
            user.setUserInformation(information);
            information.setSiptisUser(user);
        }else{return  answer;}

        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.SUCCESSFUL_USER_REGISTER, "El usuario fue registrado exitosamente");

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
        return createResponse(ServiceMessage.OK, user.getEmail());
    }
    @Override
    public ServiceAnswer getUserByEmail(String email) {
        SiptisUser user = siptisUserRepository.findOneByEmail(email).get();
        return createResponse(ServiceMessage.OK, user.getEmail());
    }
    @Override
    public Long getIdFromToken(String token){
        token = token.replace("Bearer ","");
        return JWTokenUtils.getId(token);
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
                    "El usuario no puede ser eliminado, es tutor de proyectos activo.");
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
            return createResponse(ServiceMessage.NOT_FOUND,"El usuario solicitado no se encuentra en el sistema.");
        }
        SiptisUser user = findUserById(id);
        UserInformation information = user.getUserInformation();
        if(information == null){
            return createResponse(ServiceMessage.NOT_FOUND,"No se puede acceder a la información del usuario.");
        }
        UserInformationDTO dto = new UserInformationDTO();
        dto.setEmail(user.getEmail());
        dto.setNames(information.getNames());
        dto.setLastnames(information.getLastnames());
        dto.setCi(information.getCi());
        dto.setCodSIS(information.getCodSIS());
        dto.setCelNumber(information.getCelNumber());
        dto.setBirthDate(information.getBirthDate());

        return createResponse(ServiceMessage.OK, dto);
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
    public ServiceAnswer getUserAreasById(Long id) {
        if(!existsUserById(id)){
            return createResponse(ServiceMessage.NOT_FOUND,"El usuario solicitado no se encuentra en el sistema.");
        }
        SiptisUser user = siptisUserRepository.findById(id).get();
        Set<UserArea> areas = user.getAreas();
        if(areas == null){
            return createResponse(ServiceMessage.NOT_FOUND,"No se pudo obtener las areas del usuario solicitado.");
        }
        return createResponse(ServiceMessage.OK, areas);
    }

    @Override
    public ServiceAnswer updateToken(String refreshToken) {
        RefreshToken token = refreshTokenService.findByToken(refreshToken);
        if(token == null){
            return createResponse(ServiceMessage.ERROR,"El token no existe.");
        }
        token = refreshTokenService.verifyExpirationDate(token);
        if(token == null){
            return createResponse(ServiceMessage.ERROR,"El token ya expiro.");
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
    public ServiceAnswer getUserList(String search,String role, Pageable pageable){
        return createResponse(ServiceMessage.OK, siptisUserRepository.searchUserList(search, role, pageable));
    }

    @Override
    public ServiceAnswer getNormalUserList(String search, Pageable pageable) {
        ArrayList roles = new ArrayList<String>();
        roles.add("TEACHER");

        return createResponse(ServiceMessage.OK, siptisUserRepository.searchNormalUserList(search, pageable));
    }

    @Override
    public ServiceAnswer getRoles(Long id) {
        if(!existsUserById(id)){
            return createResponse(ServiceMessage.NOT_FOUND,
                    "El usuario solicitado no se encuentra en el sistema.");
        }
        SiptisUser user = siptisUserRepository.findById(id).get();
        Set<Role> roles = user.getRoles();

        Set<String> directorRoles = roleService.directorRoles();

        if(roles == null){
            return createResponse(ServiceMessage.OK, new Role[0]);
        }

        for (String roleName: directorRoles) {
            Role role = roleRepository.findRoleByName(roleName);
            roles.remove(role);
        }

        return createResponse(ServiceMessage.OK, roles);
    }

    @Override
    public ServiceAnswer updateRoles(Long id, RolesListDTO dto) {
        if(!existsUserById(id)){
            return createResponse(ServiceMessage.NOT_FOUND,
                    "El usuario solicitado no se encuentra en el sistema.");
        }
        SiptisUser user = siptisUserRepository.findById(id).get();
        Set<Role> newRoles = new HashSet<>() ;

        Set<Role> userRoles = user.getRoles();
        Set<String> notAssignableRoles = roleService.notAssignableRoles();
        Set<String> directorRoles = roleService.directorRoles();

        for(Role role : userRoles){
            if(notAssignableRoles.contains(role.getName())){
                return createResponse(ServiceMessage.ERROR,
                        "No puede modificar los roles de este usuario.");
            }
            if(directorRoles.contains(role.getName())){
                newRoles.add(role);
            }
        }

        for(Long roleId : dto.getRoles()){
            if(!roleRepository.existsRoleById(roleId)){
                return createResponse(ServiceMessage.NOT_FOUND,
                        "El rol que desea seleccionar no existe.");
            }
            Role role = roleRepository.findRoleById(roleId);
            if(notAssignableRoles.contains(role.getName())){
                return createResponse(ServiceMessage.ERROR,
                        "No puede asignar este rol.");
            }
            newRoles.add(role);
        }


        user.setRoles(newRoles);
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, "Los roles fueron asignados correctamente");
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
    public ServiceAnswer userEditPersonalInformation(Long id, UserEditInformationDTO dto){
        if(!existsUserById(id))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, "No pudimos encontrar al usuario");

        ServiceAnswer answer;
        SiptisUser user = siptisUserRepository.findById(id).get();

        if(!user.getEmail().equals(dto.getEmail()))
            if(existsUserByEmail(dto.getEmail()))
                return createResponse(ServiceMessage.EMAIL_ALREADY_EXIST, "El correo electronico ya se encuentra ocupado.");

        UserInformation userInformation = user.getUserInformation();
        answer = userInformationService.userEditInformation(userInformation, dto);
        if(!answer.getServiceMessage().equals(ServiceMessage.OK)) {
            return answer;
        }
        user.setEmail(dto.getEmail());
        user.setUserInformation((UserInformation) answer.getData());
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, "Su cuenta fue modificada exitosamente.");
    }

    @Override
    public ServiceAnswer adminEditUserInformation(Long id, AdminEditUserInformationDTO dto) {
        if(!existsUserById(id))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, "No pudimos encontrar al usuario");
        ServiceAnswer answer;
        SiptisUser user = siptisUserRepository.findById(id).get();
        if(!user.getEmail().equals(dto.getEmail()))
            if(existsUserByEmail(dto.getEmail()))
                return createResponse(ServiceMessage.EMAIL_ALREADY_EXIST, "El correo electronico ya se encuentra ocupado.");

        UserInformation userInformation = user.getUserInformation();
        if(userInformation == null)
            return createResponse(ServiceMessage.ERROR, "No puede editar la informacion de este usuario.");
        answer = userInformationService.adminEditUserInformation(userInformation, dto);
        if(!answer.getServiceMessage().equals(ServiceMessage.OK)) {
            return answer;
        }
        user.setEmail(dto.getEmail());
        user.setUserInformation((UserInformation) answer.getData());
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, "Su cuenta fue modificada exitosamente.");
    }

    @Override
    public ServiceAnswer adminEditStudentInformation(Long id, AdminEditStudentInformationDTO dto) {
        if(!existsUserById(id))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, "No pudimos encontrar al usuario");
        ServiceAnswer answer;
        SiptisUser user = siptisUserRepository.findById(id).get();
        if(!user.getEmail().equals(dto.getEmail()))
            if(existsUserByEmail(dto.getEmail()))
                return createResponse(ServiceMessage.EMAIL_ALREADY_EXIST, "El correo electronico ya se encuentra ocupado.");

        UserInformation userInformation = user.getUserInformation();
        answer = userInformationService.adminEditStudentInformation(userInformation, dto);
        if(!answer.getServiceMessage().equals(ServiceMessage.OK)) {
            return answer;
        }
        user.setEmail(dto.getEmail());
        user.setUserInformation((UserInformation) answer.getData());
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, "Su cuenta fue modificada exitosamente.");
    }

    private ServiceAnswer registerUser(String email, String password) {
        if(existsUserByEmail(email))
            return createResponse(ServiceMessage.EMAIL_ALREADY_EXIST, "El correo electronico ya se encuentra ocupado.");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String userPassword = encoder.encode(password);
        return createResponse(ServiceMessage.OK, new SiptisUser(email, userPassword));
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage,Object data){
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }

    @Override
    public ServiceAnswer existsTokenPassword(String tokenPassword) {

        boolean response = siptisUserRepository.existsByTokenPassword(tokenPassword);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(response).build();
    }

    @Override
    public SiptisUser findByTokenPassword(String tokenPassword) {
        return siptisUserRepository.findByTokenPassword(tokenPassword).get();
    }

    @Override
    public ServiceAnswer registerUserAsCareerDirector(Long id, String directorRole) {
        if(!roleRepository.existsRoleByName(directorRole))
            return createResponse(ServiceMessage.ERROR, "El rol que desea asignar no existe.");
        if(existCareerDirector(directorRole))
            return createResponse(ServiceMessage.ERROR, "Ya existe un director de carrera en curso.");
        if(!existsUserById(id))
            return createResponse(ServiceMessage.ERROR, "No se pudo encontrar al usuario solicitado.");
        SiptisUser user = siptisUserRepository.findById(id).get();
        Set<Role> roles = user.getRoles();
        for(Role role : roles){
            if(role.getName().equals("STUDENT") || role.getName().equals("ADMIN")){
                return createResponse(ServiceMessage.ERROR,"el usuario no esta habilitado para el cargo.");
            }
        }
        user.addRol(roleRepository.findRoleByName(directorRole));
        siptisUserRepository.save(user);

        return createResponse(ServiceMessage.OK, "El cargo director fue asignado exitosamente.");
    }

    @Override
    public ServiceAnswer getDirectorPersonalInformation(String directorRole) {
        if(!existCareerDirector(directorRole))
            return createResponse(ServiceMessage.ERROR, "No existe un director de carrera en curso.");
        SiptisUser user = siptisUserRepository.findOneByRolesName(directorRole).get();
        UserInformation information = user.getUserInformation();
        UserInformationDTO dto = new UserInformationDTO();
        dto.setEmail(user.getEmail());
        dto.setNames(information.getNames());
        dto.setLastnames(information.getLastnames());
        dto.setCi(information.getCi());
        dto.setCodSIS(information.getCodSIS());
        dto.setCelNumber(information.getCelNumber());
        dto.setBirthDate(information.getBirthDate());
        String texto = information.getBirthDate().toString();
        dto.setBirthDateString(information.getBirthDate().toString());

        return createResponse(ServiceMessage.OK, dto);
    }

    @Override
    public ServiceAnswer removeDirectorRole(String directorRole) {
        if(!existCareerDirector(directorRole))
            return createResponse(ServiceMessage.ERROR, "No existe un director de carrera en curso.");
        SiptisUser user = siptisUserRepository.findOneByRolesName(directorRole).get();
        Set<Role> roles = user.getRoles();
        Role role = roleRepository.findRoleByName(directorRole);
        roles.remove(role);
        user.setRoles(roles);
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, "El cambio fue realizado correctamente.");
    }

    @Override
    public boolean existCareerDirector(String directorRole) {
        return siptisUserRepository.existsByRolesName(directorRole);
    }

    @Override
    public String getCareerDirectorName(String career) {
        String roleName = "";
        try{
            if(career.equals("INFORMATICA"))
                roleName = "INF_DIRECTOR";

            if(career.equals("SISTEMAS"))
                roleName = "SIS_DIRECTOR";

            SiptisUser user = siptisUserRepository.findOneByRolesName(roleName).get();
            UserInformation information = user.getUserInformation();
            return information.getNames() +" "+ information.getLastnames();
        }catch (Exception e){
            return null;
        }
    }



    @Override
    public ServiceAnswer getNumberStudentsCareer(Long careerId) {
        return createResponse(ServiceMessage.OK, siptisUserRepository.getNumberOfStudentsInCareer(careerId));
    }

    @Override
    public ServiceAnswer getNumberOfStudentsByYearAndCareer(Long careerId) {
        return createResponse(ServiceMessage.OK, siptisUserRepository.getNumberOfStudentsByYearAndCareer(careerId));
    }


    @Override
    public Long getProjectById(Long id) {
        Optional<Project> project = siptisUserRepository.findProjectById(id);
        if(project.isEmpty()) return null;
        Long idL = project.get().getId();
        return idL;
    }

    @Override
    public ServiceAnswer getPersonalActivities(Long id, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        Date actual = new Date(now.getYear()-1900, now.getMonthValue()-1, now.getDayOfMonth()-1);

        Page<Activity> activities = siptisUserRepository.findAllPersonalActivities(id,actual, pageable);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(activities).build();
    }

}

