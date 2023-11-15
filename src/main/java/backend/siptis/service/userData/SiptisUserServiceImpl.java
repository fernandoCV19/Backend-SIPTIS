package backend.siptis.service.userData;

import backend.siptis.auth.entity.RefreshToken;
import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.auth.jwt.JWTokenUtils;
import backend.siptis.commons.Roles;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.entity.projectManagement.Project;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.authentication.TokenDTO;
import backend.siptis.model.pjo.dto.notifications.LogInDTO;
import backend.siptis.model.pjo.dto.userDataDTO.*;
import backend.siptis.model.pjo.vo.userData.TribunalInfoToAssignSection;
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

@Service
@RequiredArgsConstructor
public class SiptisUserServiceImpl implements SiptisUserService {

    private final SiptisUserRepository siptisUserRepository;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final UserCareerService userCareerService;
    private final UserInformationService userInformationService;
    private final UserAreaService userAreaService;

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
        if (!existsUserByEmail(logInDTO.getEmail()))
            return createResponse(ServiceMessage.ERROR_BAD_CREDENTIALS, null);
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(logInDTO.getEmail(), logInDTO.getPassword()));
            if (auth.isAuthenticated()) {
                UserInformationService.UserDetailImp userDetails = (UserInformationService.UserDetailImp) auth.getPrincipal();
                TokenDTO tokenDTO = createTokenDTO(userDetails);
                return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(tokenDTO).build();
            } else {
                return createResponse(ServiceMessage.ERROR_LOG_IN, null);
            }
        } catch (Exception e) {
            return createResponse(ServiceMessage.ERROR_BAD_CREDENTIALS, null);
        }
    }

    @Override
    public ServiceAnswer registerAdmin(RegisterAdminDTO dto) {
        ServiceAnswer answer = registerUser(dto.getEmail(), dto.getPassword());
        SiptisUser user;
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            user = (SiptisUser) answer.getData();
        } else {
            return answer;
        }

        answer = roleService.getRoleByName(Roles.ADMIN.toString());
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            Role role = (Role) answer.getData();
            user.addRol(role);
        } else {
            return answer;
        }

        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.SUCCESSFUL_REGISTER, null);
    }


    @Override
    public ServiceAnswer registerStudent(RegisterStudentDTO dto) {
        ServiceAnswer answer = registerUser(dto.getEmail(), dto.getCi());
        SiptisUser user;
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            user = (SiptisUser) answer.getData();
        } else {
            return answer;
        }
        answer = userInformationService.registerUserInformation(dto);
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            UserInformation information = (UserInformation) answer.getData();
            user.setUserInformation(information);
            information.setSiptisUser(user);
        } else {
            return answer;
        }

        answer = roleService.getRoleByName(Roles.STUDENT.toString());
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            Role role = (Role) answer.getData();
            user.addRol(role);
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
        return createResponse(ServiceMessage.SUCCESSFUL_REGISTER, createUserInformationDTO(user));

    }

    @Override
    public ServiceAnswer registerUser(RegisterUserDTO dto) {
        ServiceAnswer answer = registerUser(dto.getEmail(), dto.getCi());
        SiptisUser user;
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            user = (SiptisUser) answer.getData();
        } else {
            return answer;
        }

        answer = userInformationService.registerUserInformation(dto);
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            UserInformation information = (UserInformation) answer.getData();
            user.setUserInformation(information);
            information.setSiptisUser(user);
        } else {
            return answer;
        }

        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.SUCCESSFUL_REGISTER, createUserInformationDTO(user));

    }


    @Override
    public ServiceAnswer getAllUsers() {
        List<SiptisUser> userList = siptisUserRepository.findAll();
        return userList.isEmpty() ?
                ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(userList).build() :
                ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(siptisUserRepository.findAll()).build();
    }


    @Override
    public Long getIdFromToken(String token) {
        token = token.replace("Bearer ", "");
        return JWTokenUtils.getId(token);
    }

    @Override
    public ArrayList<?> getProjectsFromToken(String token) {
        token = token.replace("Bearer ", "");
        return JWTokenUtils.getProjects(token);
    }

    @Override
    public ServiceAnswer deleteUser(Long id) {
        if (!siptisUserRepository.existsById(id))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, null);
        SiptisUser user = siptisUserRepository.findById(id).get();
        ServiceAnswer answer = deleteValidations(user);
        if (answer != null) {
            return answer;
        }
        siptisUserRepository.deleteById(id);
        return createResponse(ServiceMessage.USER_DELETED, null);
    }

    private ServiceAnswer deleteValidations(SiptisUser user) {
        if (user.getStudents() != null && user.getStudents().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        if (user.getAvailableSchedules() != null && user.getAvailableSchedules().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        if (user.getDocuments() != null && user.getDocuments().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        if (user.getReviews() != null && user.getReviews().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        if (user.getSupervisorOf() != null && user.getSupervisorOf().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        if (user.getTeacherOf() != null && user.getTeacherOf().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        if (user.getTutorOf() != null && user.getTutorOf().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }

        if (user.getTribunalOf() != null && user.getTribunalOf().size() > 0) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }

        return null;
    }


    @Override
    public ServiceAnswer getUserPersonalInformation(Long id) {
        if (!existsUserById(id))
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = findUserById(id);
        return createResponse(ServiceMessage.OK, createUserInformationDTO(user));
    }

    @Override
    public ServiceAnswer getStudentCareerById(Long id) {
        if (!existsUserById(id))
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = findUserById(id);
        Set<UserCareer> career = user.getCareer();
        return createResponse(ServiceMessage.OK, career);
    }

    @Override
    public ServiceAnswer getUserAreasById(Long id) {
        if (!existsUserById(id))
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = siptisUserRepository.findById(id).get();
        Set<UserArea> areas = user.getAreas();
        return createResponse(ServiceMessage.OK, areas);
    }

    @Override
    public ServiceAnswer updateToken(String refreshToken) {
        RefreshToken token = refreshTokenService.findByToken(refreshToken);
        if (token == null) {
            return createResponse(ServiceMessage.NOT_FOUND, null);
        }
        if (!refreshTokenService.verifyValidExpirationDate(token)) {
            return createResponse(ServiceMessage.EXPIRED_REFRESH_TOKEN, null);
        }
        SiptisUser user = token.getSiptisUser();
        String updatedToken = JWTokenUtils.createToken(new UserInformationService.UserDetailImp(user));
        RefreshToken newRefreshToken = refreshTokenService.createRefreshToken(user);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(updatedToken);
        tokenDTO.setRefreshToken(newRefreshToken.getToken());

        return createResponse(ServiceMessage.OK, tokenDTO);
    }

    @Override
    public ServiceAnswer getUserList(String search, String role, Pageable pageable) {
        return createResponse(ServiceMessage.OK, siptisUserRepository.searchUserList(search, role, pageable));
    }

    @Override
    public ServiceAnswer getNormalUserList(String search, Pageable pageable) {
        return createResponse(ServiceMessage.OK, siptisUserRepository.searchNormalUserList(search, pageable));
    }

    @Override
    public ServiceAnswer getAdminUserList(String search, Pageable pageable) {
        return createResponse(ServiceMessage.OK, siptisUserRepository.searchAdminList(search, pageable));
    }

    @Override
    public ServiceAnswer getRolesById(Long id) {
        if (!existsUserById(id))
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = siptisUserRepository.findById(id).get();
        Set<Role> roles = user.getRoles();
        Set<String> directorRoles = roleService.directorRoles();
        if (roles == null)
            return createResponse(ServiceMessage.OK, new Role[0]);
        for (String roleName : directorRoles) {
            Role role = roleRepository.findRoleByName(roleName);
            roles.remove(role);
        }
        return createResponse(ServiceMessage.OK, roles);
    }

    @Override
    public ServiceAnswer updateRoles(Long id, RolesListDTO dto) {
        if (!existsUserById(id))
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = siptisUserRepository.findById(id).get();
        Set<Role> newRoles = new HashSet<>();
        Set<Role> userRoles = user.getRoles();
        Set<String> notAssignableRoles = roleService.notAssignableRoles();
        Set<String> directorRoles = roleService.directorRoles();
        for (Role role : userRoles) {
            if (notAssignableRoles.contains(role.getName()))
                return createResponse(ServiceMessage.ERROR, null);
            if (directorRoles.contains(role.getName()))
                newRoles.add(role);
        }
        for (Long roleId : dto.getRoles()) {
            if (!roleRepository.existsRoleById(roleId))
                return createResponse(ServiceMessage.NOT_FOUND, null);
            Role role = roleRepository.findRoleById(roleId);
            if (notAssignableRoles.contains(role.getName()))
                return createResponse(ServiceMessage.ERROR, null);
            newRoles.add(role);
        }
        user.setRoles(newRoles);
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, user.getRoles());
    }

    @Override
    public ServiceAnswer updateAreas(Long id, UserSelectedAreasDTO dto) {
        if (!existsUserById(id))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, null);
        List<Long> ids = dto.getIds();
        Set<UserArea> areas = new HashSet<>();
        for (Long areaId : ids) {
            if (!userAreaService.userAreaExistById(areaId.intValue()))
                return createResponse(ServiceMessage.NOT_FOUND, null);
            areas.add(userAreaService.getUserAreaById(areaId.intValue()));
        }
        SiptisUser user = findUserById(id);
        user.setAreas(areas);
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, user.getAreas());
    }


    @Override
    public ServiceAnswer userEditPersonalInformation(Long id, UserEditInformationDTO dto) {
        if (!existsUserById(id))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, null);
        ServiceAnswer answer;
        SiptisUser user = siptisUserRepository.findById(id).get();
        if (!user.getEmail().equals(dto.getEmail()))
            if (existsUserByEmail(dto.getEmail()))
                return createResponse(ServiceMessage.EMAIL_ALREADY_EXIST, null);
        UserInformation userInformation = user.getUserInformation();
        answer = userInformationService.userEditInformation(userInformation, dto);
        if (!answer.getServiceMessage().equals(ServiceMessage.OK))
            return answer;
        user.setEmail(dto.getEmail());
        user.setUserInformation((UserInformation) answer.getData());
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, createUserInformationDTO(user));
    }

    @Override
    public ServiceAnswer adminEditUserInformation(Long id, AdminEditUserInformationDTO dto) {
        if (!existsUserById(id))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, null);
        ServiceAnswer answer;
        SiptisUser user = siptisUserRepository.findById(id).get();

        if (!user.getEmail().equals(dto.getEmail()))
            if (existsUserByEmail(dto.getEmail()))
                return createResponse(ServiceMessage.EMAIL_ALREADY_EXIST, null);
        UserInformation userInformation = user.getUserInformation();
        if (userInformation == null)
            return createResponse(ServiceMessage.ERROR, null);
        boolean isStudent = false;
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getName().equals(Roles.STUDENT.toString()))
                isStudent = true;
        }
        if (isStudent) {
            answer = userInformationService.adminEditStudentInformation(userInformation, dto);
        } else {
            answer = userInformationService.adminEditUserInformation(userInformation, dto);
        }
        if (!answer.getServiceMessage().equals(ServiceMessage.OK))
            return answer;
        user.setEmail(dto.getEmail());
        user.setUserInformation((UserInformation) answer.getData());
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, createUserInformationDTO(user));
    }

    private ServiceAnswer registerUser(String email, String password) {
        email = email.trim();
        if (existsUserByEmail(email))
            return createResponse(ServiceMessage.EMAIL_ALREADY_EXIST, null);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String userPassword = encoder.encode(password);
        return createResponse(ServiceMessage.OK, new SiptisUser(email, userPassword));
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }

    @Override
    public ServiceAnswer existsTokenPassword(String tokenPassword) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(siptisUserRepository.existsByTokenPassword(tokenPassword)).build();
    }

    @Override
    public SiptisUser findByTokenPassword(String tokenPassword) {
        return siptisUserRepository.findByTokenPassword(tokenPassword).get();
    }

    @Override
    public ServiceAnswer registerUserAsCareerDirector(Long id, String directorRole) {
        if (!roleRepository.existsRoleByName(directorRole))
            return createResponse(ServiceMessage.ERROR, null);
        if (existCareerDirector(directorRole))
            return createResponse(ServiceMessage.DIRECTOR_ALREADY_EXIST, null);
        if (!existsUserById(id))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, null);
        SiptisUser user = siptisUserRepository.findById(id).get();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getName().equals(Roles.STUDENT.toString()) || role.getName().equals(Roles.ADMIN.toString()))
                return createResponse(ServiceMessage.CANNOT_ASSIGN_ROLE, null);
        }
        user.addRol(roleRepository.findRoleByName(directorRole));
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, createUserInformationDTO(user));
    }

    @Override
    public ServiceAnswer getDirectorPersonalInformation(String directorRole) {
        if (!existCareerDirector(directorRole))
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = siptisUserRepository.findOneByRolesName(directorRole).get();
        return createResponse(ServiceMessage.OK, createUserInformationDTO(user));
    }

    @Override
    public ServiceAnswer removeDirectorRole(String directorRole) {
        if (!existCareerDirector(directorRole))
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = siptisUserRepository.findOneByRolesName(directorRole).get();
        Set<Role> roles = user.getRoles();
        Role role = roleRepository.findRoleByName(directorRole);
        roles.remove(role);
        user.setRoles(roles);
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, createUserInformationDTO(user));
    }

    @Override
    public boolean existCareerDirector(String directorRole) {
        return siptisUserRepository.existsByRolesName(directorRole);
    }

    @Override
    public String getCareerDirectorName(String career) {
        String roleName = "";
        try {
            if (career.equals(backend.siptis.commons.UserCareer.INFORMATICA.toString()))
                roleName = Roles.INF_DIRECTOR.toString();
            if (career.equals(backend.siptis.commons.UserCareer.SISTEMAS.toString()))
                roleName = Roles.SIS_DIRECTOR.toString();
            SiptisUser user = siptisUserRepository.findOneByRolesName(roleName).get();
            UserInformation information = user.getUserInformation();
            return information.getNames() + " " + information.getLastNames();
        } catch (Exception e) {
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
        if (project.isEmpty()) return null;
        Long idL = project.get().getId();
        return idL;
    }

    @Override
    public ServiceAnswer getPersonalActivities(Long id, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        Date actual = new Date(now.getYear() - 1900, now.getMonthValue() - 1, now.getDayOfMonth() - 1);

        Page<Activity> activities = siptisUserRepository.findAllPersonalActivities(id, actual, pageable);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(activities).build();
    }

    private UserInformationDTO createUserInformationDTO(SiptisUser user) {
        UserInformation information = user.getUserInformation();
        if (information == null) {
            return null;
        }
        UserInformationDTO dto = new UserInformationDTO();
        dto.setEmail(user.getEmail());
        dto.setNames(information.getNames());
        dto.setLastnames(information.getLastNames());
        dto.setCi(information.getCi());
        dto.setCodSIS(information.getCodSIS());
        dto.setCelNumber(information.getCelNumber());
        dto.setBirthDate(information.getBirthDate());
        dto.setBirthDateString(information.getBirthDate().toString());
        return dto;
    }
}

