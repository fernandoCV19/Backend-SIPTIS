package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.UserInformationDTO;
import backend.siptis.model.repository.auth.RoleRepository;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Transactional
@RequiredArgsConstructor
@Service
public class SiptisUserServiceDelete {

    private final SiptisUserRepository siptisUserRepository;
    private final RoleRepository roleRepository;

    public ServiceAnswer deleteUser(Long id) {
        Optional<SiptisUser> oUser = siptisUserRepository.findById(id);
        if (oUser.isEmpty())
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, null);
        SiptisUser user = oUser.get();
        ServiceAnswer answer = deleteValidations(user);
        if (answer != null) {
            return answer;
        }
        siptisUserRepository.deleteById(id);
        return createResponse(ServiceMessage.USER_DELETED, null);
    }

    public ServiceAnswer removeDirectorRole(String directorRole) {
        Optional<SiptisUser> oUser = siptisUserRepository.findOneByRolesName(directorRole);
        if (oUser.isEmpty())
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = oUser.get();
        Set<Role> roles = user.getRoles();
        Role role = roleRepository.findRoleByName(directorRole);
        roles.remove(role);
        user.setRoles(roles);
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, new UserInformationDTO(user));
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }

    public boolean existCareerDirector(String directorRole) {
        return siptisUserRepository.existsByRolesName(directorRole);
    }

    private ServiceAnswer deleteValidations(SiptisUser user) {
        if (user.getStudents() != null && !user.getStudents().isEmpty()) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        if (user.getAvailableSchedules() != null && !user.getAvailableSchedules().isEmpty()) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        if (user.getReviews() != null && !user.getReviews().isEmpty()) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        if (user.getSupervisorOf() != null && !user.getSupervisorOf().isEmpty()) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        if (user.getTeacherOf() != null && !user.getTeacherOf().isEmpty()) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        if (user.getTutorOf() != null && !user.getTutorOf().isEmpty()) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        if (user.getTribunalOf() != null && !user.getTribunalOf().isEmpty()) {
            return createResponse(ServiceMessage.ERROR_CANNOT_DELETE_USER, null);
        }
        return null;
    }
}
