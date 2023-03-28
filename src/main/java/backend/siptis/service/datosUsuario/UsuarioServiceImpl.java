package backend.siptis.service.datosUsuario;

import backend.siptis.auth.entity.Usuario;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.repository.datosUsuario.UsuarioCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioCommonService{

    private final UsuarioCommonRepository usuarioCommonRepository;

    @Override
    public ServiceAnswer obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Long id) {
        Optional<SiptisUser> usuarioOptional = usuarioCommonRepository.findById(id);
        if(usuarioOptional.isEmpty()){
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }

        return null;
    }
}
