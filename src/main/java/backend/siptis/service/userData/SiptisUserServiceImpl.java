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
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.UserEditPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;
import backend.siptis.model.pjo.dto.authentication.TokenDTO;
import backend.siptis.model.pjo.dto.notifications.LogInDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.*;
import backend.siptis.model.pjo.vo.ActivityVO;
import backend.siptis.model.pjo.vo.userData.TribunalInfoToAssignSection;
import backend.siptis.model.repository.notifications.ActivityRepository;
import backend.siptis.model.repository.projectManagement.ProjectRepository;
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
    private final ProjectRepository projectRepository;
    private final ActivityRepository activityRepository;


    @Override
    public ServiceAnswer getPossibleTribunals() {
        List<SiptisUser> query = siptisUserRepository.findByRolesName("TRIBUNAL");
        List<TribunalInfoToAssignSection> res = query.stream().map(TribunalInfoToAssignSection::new).toList();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(res).build();
    }

    private boolean existsUserByEmail(String email) {
        return siptisUserRepository.existsByEmail(email);
    }

    private boolean existsUserById(long id) {
        return siptisUserRepository.existsById(id);
    }

    private SiptisUser findUserById(long id) {
        return siptisUserRepository.findById(id).get();
    }

    private TokenDTO createTokenDTO(UserInformationService.UserDetailImp userDetails) {
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails);
        String token = JWTokenUtils.createToken(userDetails);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);
        tokenDTO.setRefreshToken(refreshToken.getToken());
        return tokenDTO;
    }

    @Override
    public ServiceAnswer logIn(LogInDTO logInDTO) {

        if (!existsUserByEmail(logInDTO.getEmail())) {
            String errorMessage = "Credenciales incorrectas.";
            return createResponse(ServiceMessage.ERROR_BAD_CREDENTIALS, errorMessage);
        }

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            logInDTO.getEmail(), logInDTO.getPassword())
            );

            if (auth.isAuthenticated()) {
                UserInformationService.UserDetailImp userDetails = (UserInformationService.UserDetailImp) auth.getPrincipal();
                TokenDTO tokenDTO = createTokenDTO(userDetails);

                return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(tokenDTO).build();

            } else {
                return createResponse(ServiceMessage.ERROR_LOG_IN, "Ocurrió un error al iniciar Sesión.");
            }
        } catch (Exception e) {
            System.out.println("Error de autenticación: " + e.getMessage());
            return createResponse(ServiceMessage.ERROR_BAD_CREDENTIALS, "Credenciales incorrectas.");
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
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            user = (SiptisUser) answer.getData();
        } else {
            return answer;
        }

        answer = roleService.getRoleByName("ADMIN");
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            Role role = (Role) answer.getData();
            user.addRol(role);
        } else {
            return answer;
        }

        siptisUserRepository.save(user);
        return createResponse
                (ServiceMessage.OK, "El administrador fue registrado exitosamente");

    }

    @Override
    public ServiceAnswer registerTeacher(RegisterUserDTO dto) {

        ServiceAnswer answer = registerRoleAndInformation(dto, "TEACHER");
        SiptisUser user;
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            user = (SiptisUser) answer.getData();
        } else {
            return answer;
        }

        siptisUserRepository.save(user);
        return createResponse
                (ServiceMessage.OK, "El docente fue registrado exitosamente");
    }


    @Override
    public ServiceAnswer registerStudent(RegisterStudentDTO dto) {


        ServiceAnswer answer = registerRoleAndInformation(dto, "STUDENT");
        SiptisUser user;

        if (answer.getServiceMessage() == ServiceMessage.OK) {
            user = (SiptisUser) answer.getData();
        } else {
            return answer;
        }

        answer = userCareerService.getCareerByName(dto.getCareer());
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            UserCareer career = (UserCareer) answer.getData();
            user.addCareer(career);
        } else {
            return answer;
        }
        siptisUserRepository.save(user);
        return createResponse
                (ServiceMessage.OK, "El estudiante fue registrado exitosamente");

    }

    @Override
    public ServiceAnswer registerSpecialUser(RegisterSpecialUserDTO dto) {
        ServiceAnswer answer = registerRoleAndInformation(dto, "SPECIAL_USER");
        SiptisUser user;

        if (answer.getServiceMessage() == ServiceMessage.OK) {
            user = (SiptisUser) answer.getData();
        } else {
            return answer;
        }

        siptisUserRepository.save(user);
        return createResponse
                (ServiceMessage.OK, "El usuario fue registrado exitosamente");
    }

    @Override
    public ServiceAnswer updateToken(String refreshToken) {
        RefreshToken token = refreshTokenService.findByToken(refreshToken);
        if (token == null) {
            return createResponse(ServiceMessage.ERROR, "El token no existe");
        }
        token = refreshTokenService.verifyExpirationDate(token);
        if (token == null) {
            return createResponse(ServiceMessage.ERROR, "El token ya expiro");
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
    public Long getIdFromToken(String token) {
        token = token.replace("Bearer ", "");
        return JWTokenUtils.getId(token);
    }

    @Override
    public ServiceAnswer getStudentList(String search, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = (pageNumber - 1) * pageSize;
        return createResponse(ServiceMessage.OK,
                siptisUserRepository.searchUserList(search, "STUDENT", pageable));
        //siptisUserRepository.searchUser(search));
    }

    @Override
    public ServiceAnswer getTeacherList(String search, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = (pageNumber - 1) * pageSize;
        return createResponse(ServiceMessage.OK,
                siptisUserRepository.searchUserList(search, "TEACHER", pageable));
        //siptisUserRepository.searchUser(search));
    }

    @Override
    public ServiceAnswer getSpecialUserList(String search, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = (pageNumber - 1) * pageSize;
        return createResponse(ServiceMessage.OK,
                siptisUserRepository.searchUserList(search, "SPECIAL_USER", pageable));
        //siptisUserRepository.searchUser(search));
    }

    @Override
    public ServiceAnswer getAdminList(Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = (pageNumber - 1) * pageSize;
        return createResponse(ServiceMessage.OK,
                siptisUserRepository.searchAdminList("ADMIN", pageable));
        //siptisUserRepository.searchUser(search));
    }

    @Override
    public ServiceAnswer getPotentialTutorsList(String search, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int offset = (pageNumber - 1) * pageSize;
        return createResponse(ServiceMessage.OK,
                siptisUserRepository.searchPotentialTutorsList(search, "SPECIAL_USER", "TEACHER", pageable));
        //siptisUserRepository.searchUser(search));
    }

    @Override
    public ServiceAnswer deleteUser(Long id) {
        if (!siptisUserRepository.existsById(id)) {
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, "No pudimos encontrar al usuario");
        }
        SiptisUser user = siptisUserRepository.findById(id).get();
        ServiceAnswer answer = deleteValidations(user);
        if (answer != null) {
            return answer;
        }
        siptisUserRepository.deleteById(id);
        return createResponse(ServiceMessage.USER_DELETED, "El usuario fue eliminado");
    }

    private ServiceAnswer deleteValidations(SiptisUser user) {
        if (user.getStudents().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, tiene un proyecto en curso.");
        }
        if (user.getAvailableSchedules().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, tiene horarios en curso.");
        }
        if (user.getDocuments().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, tiene documentos asociados.");
        }
        if (user.getReviews().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, es revisor de proyectos activo.");
        }
        if (user.getSupervisorOf().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, es supervisor de proyectos activo.");
        }
        if (user.getTeacherOf().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, es docente de proyectos activo.");
        }
        if (user.getTutorOf().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, es tutol de proyectos activo.");
        }

        if (user.getTribunalOf().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER,
                    "El usuario no puede ser eliminado, es tribunal de proyectos activo.");
        }

        return null;
    }

    @Override
    public ServiceAnswer getUserPersonalInformation(Long id) {
        if (!existsUserById(id)) {
            return createResponse(ServiceMessage.NOT_FOUND,
                    "El usuario solicitado no se encuentra en el sistema.");
        }
        SiptisUser user = findUserById(id);

        return userInformationService.getUserPersonalInformation(user);
    }

    @Override
    public ServiceAnswer getTeacherAreasById(Long id) {
        if (!existsUserById(id)) {
            return createResponse(ServiceMessage.NOT_FOUND,
                    "El usuario solicitado no se encuentra en el sistema.");
        }
        SiptisUser user = siptisUserRepository.findById(id).get();
        Set<UserArea> areas = user.getAreas();
        if (areas == null) {
            return createResponse(ServiceMessage.NOT_FOUND,
                    "No se pudo obtener las areas del usuario solicitado.");
        }
        return createResponse(ServiceMessage.OK, areas);
    }

    @Override
    public ServiceAnswer getTeacherNotSelectedAreasById(Long id) {
        if (!existsUserById(id)) {
            return createResponse(ServiceMessage.NOT_FOUND,
                    "El usuario solicitado no se encuentra en el sistema.");
        }
        return createResponse(ServiceMessage.OK, siptisUserRepository.getNotSelectedAreas(id));
    }

    @Override
    public ServiceAnswer updateAreas(Long id, UserSelectedAreasDTO dto) {
        if (!existsUserById(id)) {
            return createResponse(ServiceMessage.NOT_FOUND,
                    "El usuario solicitado no se encuentra en el sistema.");
        }
        List<Long> ids = dto.getIds();
        Set<UserArea> areas = new HashSet<>();
        for (Long areaId : ids) {
            if (!userAreaService.userAreaExistById(areaId.intValue())) {
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
        if (!existsUserById(id)) {
            String errorMessage = "El usuario al que intenta acceder no se encuentra registrado en el sistema";
            return createResponse(ServiceMessage.ERROR, errorMessage);
        }
        SiptisUser user = findUserById(id);
        Set<UserCareer> career = user.getCareer();
        return createResponse(ServiceMessage.OK, career);

    }

    @Override
    public ServiceAnswer userEditPersonalInformation(Long id, UserEditPersonalInformationDTO dto) {
        if (!existsUserById(id))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, "No pudimos encontrar al usuario");

        ServiceAnswer answer;
        SiptisUser user = siptisUserRepository.findById(id).get();

        if (!user.getEmail().equals(dto.getEmail()))
            if (existsUserByEmail(dto.getEmail()))
                return createResponse(ServiceMessage.ERROR, "El correo electronico ya se encuentra ocupado.");


        UserInformation userInformation = user.getUserInformation();
        answer = userInformationService.userEditLimitedInformation(userInformation, dto);
        if (!answer.getServiceMessage().equals(ServiceMessage.OK)) {
            return answer;
        }
        user.setEmail(dto.getEmail());
        user.setUserInformation((UserInformation) answer.getData());
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, "Su cuenta fue modificada exitosamente.");
    }

    @Override
    public ServiceAnswer adminEditUserPersonalInformation(Long id, UniversityUserPersonalInformationDTO dto) {
        if (!existsUserById(id))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, "No pudimos encontrar al usuario");

        ServiceAnswer answer;
        SiptisUser user = siptisUserRepository.findById(id).get();

        if (!user.getEmail().equals(dto.getEmail()))
            if (existsUserByEmail(dto.getEmail()))
                return createResponse(ServiceMessage.ERROR, "El correo electronico ya se encuentra ocupado.");


        UserInformation userInformation = user.getUserInformation();
        answer = userInformationService.adminEditUserFullInformation(userInformation, dto);
        if (!answer.getServiceMessage().equals(ServiceMessage.OK)) {
            return answer;
        }
        user.setEmail(dto.getEmail());
        user.setUserInformation((UserInformation) answer.getData());
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, "Su cuenta fue modificada exitosamente.");
    }

    @Override
    public ServiceAnswer adminEditSpecialUserPersonalInformation(Long id, GeneralUserPersonalInformationDTO dto) {
        if (!existsUserById(id))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, "No pudimos encontrar al usuario");

        ServiceAnswer answer;
        SiptisUser user = siptisUserRepository.findById(id).get();

        if (!user.getEmail().equals(dto.getEmail()))
            if (existsUserByEmail(dto.getEmail()))
                return createResponse(ServiceMessage.ERROR, "El correo electronico ya se encuentra ocupado.");


        UserInformation userInformation = user.getUserInformation();
        answer = userInformationService.adminEditUserFullInformation(userInformation, dto);
        if (!answer.getServiceMessage().equals(ServiceMessage.OK)) {
            return answer;
        }
        user.setEmail(dto.getEmail());
        user.setUserInformation((UserInformation) answer.getData());
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, "Su cuenta fue modificada exitosamente.");
    }


    private ServiceAnswer registerUser(String email, String password) {

        if (existsUserByEmail(email))
            return createResponse(ServiceMessage.ERROR_REGISTER_ACCOUNT_EMAIL, "El correo electronico ya se encuentra ocupado.");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String userPassword = encoder.encode(password);
        return createResponse(ServiceMessage.OK, new SiptisUser(email, userPassword));
    }

    private ServiceAnswer registerRoleAndInformation(RegisterUserDTO dto, String career) {
        ServiceAnswer answer = registerUser(dto.getEmail(), dto.getPassword());
        SiptisUser user;

        if (answer.getServiceMessage() == ServiceMessage.OK) {
            user = (SiptisUser) answer.getData();
        } else {
            return answer;
        }

        answer = userInformationService.registerUserInformation(dto, user);
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            UserInformation info = (UserInformation) answer.getData();
            user.setUserInformation(info);
        } else {
            return answer;
        }

        answer = roleService.getRoleByName(career);
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            Role role = (Role) answer.getData();
            user.addRol(role);
        } else {
            return answer;
        }

        return createResponse(ServiceMessage.OK, user);
    }

    private ServiceAnswer registerRoleAndInformation(RegisterSpecialUserDTO dto, String career) {
        ServiceAnswer answer = registerUser(dto.getEmail(), dto.getPassword());
        SiptisUser user;

        if (answer.getServiceMessage() == ServiceMessage.OK) {
            user = (SiptisUser) answer.getData();
        } else {
            return answer;
        }

        answer = userInformationService.registerSpecialUserInformation(dto, user);
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            UserInformation info = (UserInformation) answer.getData();
            user.setUserInformation(info);
        } else {
            return answer;
        }

        answer = roleService.getRoleByName(career);
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            Role role = (Role) answer.getData();
            user.addRol(role);
        } else {
            return answer;
        }

        return createResponse(ServiceMessage.OK, user);
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }

    @Override
    public Long getProjectById(Long id) {
        Optional<Project> project = siptisUserRepository.findProjectById(id);
        if (project.isEmpty()) return null;
        Long idL = project.get().getId();
        return idL;
    }

    @Override
    public ServiceAnswer getPersonalActivities(Long id, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        Date actual = new Date(now.getYear() - 1900, now.getMonthValue() - 1, now.getDayOfMonth() - 1);
        Long idProject = getProjectById(id);
        Page<ActivityVO> activitiesFromUser = activityRepository.findByProjectId(idProject, pageable);
        Page<Activity> activities = siptisUserRepository.findAllPersonalActivities(idProject, actual, pageable);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(activitiesFromUser).build();
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
    public ServiceAnswer userEditPersonalInformation() {
        return null;
    }

    @Override
    public ServiceAnswer adminEditUserPersonalInformation() {
        return null;
    }

    @Override
    public ServiceAnswer getPersonalActivities(Long id, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        Date actual = new Date(now.getYear()-1900, now.getMonthValue()-1, now.getDayOfMonth()-1);

        Page<Activity> activities = usuarioCommonRepository.findAllPersonalActivities(id,actual, pageable);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(activities).build();
    }

    private UserGeneralInformationDTO convertToDTO(SiptisUser user) {
        UserGeneralInformationDTO userDTO = new UserGeneralInformationDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
*/
}

