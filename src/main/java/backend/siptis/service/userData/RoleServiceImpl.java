package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.repository.general.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public ServiceAnswer getRoleByName(String name) {

        if (!roleRepository.existsRoleByName(name)) {
            return createResponse(ServiceMessage.ERROR, "No se pudo encotrar el rol.");
        }


        return createResponse(ServiceMessage.OK, roleRepository.findRoleByName(name));
    }


    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }
}
