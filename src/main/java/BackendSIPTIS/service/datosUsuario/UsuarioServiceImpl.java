package BackendSIPTIS.service.datosUsuario;

import BackendSIPTIS.auth.entity.Usuario;
import BackendSIPTIS.commons.MensajeServicio;
import BackendSIPTIS.commons.RespuestaServicio;
import BackendSIPTIS.model.repository.datosUsuario.UsuarioCommonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioCommonService{

    private final UsuarioCommonRepository usuarioCommonRepository;

    @Override
    public RespuestaServicio obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Long id) {
        Optional<Usuario> usuarioOptional = usuarioCommonRepository.findById(id);
        if(usuarioOptional.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.NOT_FOUND).data(null).build();
        }

        return null;
    }
}
