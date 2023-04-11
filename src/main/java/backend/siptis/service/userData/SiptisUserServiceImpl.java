package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiptisUserServiceImpl implements SiptisUserService {

    private final SiptisUserRepository usuarioCommonRepository;

    @Override
    public ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Long id) {
        Optional<SiptisUser> usuarioOptional = usuarioCommonRepository.findById(id);
        if(usuarioOptional.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }

        return null;
    }
}
