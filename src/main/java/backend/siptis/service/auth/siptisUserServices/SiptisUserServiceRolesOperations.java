package backend.siptis.service.auth.siptisUserServices;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.RolesListDTO;
import backend.siptis.model.repository.auth.RoleRepository;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.service.auth.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class SiptisUserServiceRolesOperations {

    private final SiptisUserServiceExistValidation siptisUserServiceExistValidation;
    private final SiptisUserRepository siptisUserRepository;
    private final RoleService roleService;
    private final RoleRepository roleRepository;

    public ServiceAnswer getRolesById(Long id) {
        Optional<SiptisUser> userOptional = siptisUserRepository.findById(id);
        if (userOptional.isEmpty())
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = userOptional.get();
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

    public ServiceAnswer updateRoles(Long id, RolesListDTO dto) {
        Optional<SiptisUser> userOptional = siptisUserRepository.findById(id);
        if (userOptional.isEmpty())
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = userOptional.get();
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

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }
}
