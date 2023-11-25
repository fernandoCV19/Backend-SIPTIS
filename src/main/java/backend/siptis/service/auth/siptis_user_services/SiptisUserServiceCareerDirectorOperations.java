package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.Roles;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.user_data.UserInformation;
import backend.siptis.model.pjo.dto.user_data.UserInformationDTO;
import backend.siptis.model.repository.auth.RoleRepository;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class SiptisUserServiceCareerDirectorOperations {

    private final RoleRepository roleRepository;
    private final SiptisUserRepository siptisUserRepository;
    private final SiptisUserServiceExistValidation siptisUserServiceExistValidation;

    public ServiceAnswer registerUserAsCareerDirector(Long id, String directorRole) {
        if (!roleRepository.existsRoleByName(directorRole))
            return createResponse(ServiceMessage.DIRECTOR_ROLE_NOT_FOUND, null);
        if (siptisUserServiceExistValidation.existCareerDirector(directorRole))
            return createResponse(ServiceMessage.DIRECTOR_ALREADY_EXIST, null);
        Optional<SiptisUser> userOptional = siptisUserRepository.findById(id);
        if (userOptional.isEmpty())
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = userOptional.get();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getName().equals(Roles.STUDENT.toString()) || role.getName().equals(Roles.ADMIN.toString()))
                return createResponse(ServiceMessage.CANNOT_ASSIGN_ROLE, null);
        }
        user.addRol(roleRepository.findRoleByName(directorRole));
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, new UserInformationDTO(user));
    }

    public ServiceAnswer getDirectorPersonalInformation(String directorRole) {
        Optional<SiptisUser> oUser = siptisUserRepository.findOneByRolesName(directorRole);
        if (oUser.isEmpty())
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = oUser.get();
        return createResponse(ServiceMessage.OK, new UserInformationDTO(user));
    }

    public String getCareerDirectorName(String career) {
        String roleName = "";
        try {
            if (career.equals(backend.siptis.commons.UserCareer.INFORMATICA.toString()))
                roleName = Roles.INF_DIRECTOR.toString();
            if (career.equals(backend.siptis.commons.UserCareer.SISTEMAS.toString()))
                roleName = Roles.SIS_DIRECTOR.toString();
            Optional<SiptisUser> oUser = siptisUserRepository.findOneByRolesName(roleName);
            if (oUser.isEmpty())
                return null;
            SiptisUser user = oUser.get();
            UserInformation information = user.getUserInformation();
            return information.getNames() + " " + information.getLastNames();
        } catch (Exception e) {
            return null;
        }
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }
}
