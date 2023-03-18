package backend.siptis.service.datosUsuario;

import BackendSIPTIS.auth.entity.Usuario;
import backend.siptis.commons.MensajeServicio;
import backend.siptis.commons.RespuestaServicio;
import backend.siptis.model.repository.datosUsuario.UsuarioCommonRepository;
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
