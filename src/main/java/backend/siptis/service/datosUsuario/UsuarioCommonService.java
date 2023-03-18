package backend.siptis.service.datosUsuario;

import backend.siptis.commons.RespuestaServicio;

public interface UsuarioCommonService {

    RespuestaServicio obtenerProyectosSupervisorParaMenuPrincipalPorIdUsuario(Long id);

}
