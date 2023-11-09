package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.repository.userData.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;
    @Override
    public ServiceAnswer getAllowedRoles() {
        return createResponse(ServiceMessage.OK,roleRepository.getAllowedRoles());
    }

    @Override
    public ServiceAnswer getRoleByName(String name) {

        if(!roleRepository.existsRoleByName(name)){
            return createResponse(ServiceMessage.ERROR, null);
        }
        return createResponse(ServiceMessage.OK, roleRepository.findRoleByName(name));
    }

    @Override
    public Set<String> assignableRoles() {
        return new HashSet<>(Set.of("TEACHER", "TUTOR", "SUPERVISOR", "TRIBUNAL", "REVIEWER"));
    }

    @Override
    public Set<String> notAssignableRoles() {
        return new HashSet<>(Set.of("STUDENT", "ADMIN"));
    }

    @Override
    public Set<String> directorRoles() {
        return new HashSet<>(Set.of("INF_DIRECTOR", "SIS_DIRECTOR"));
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data){
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }
}
