package BackendSIPTIS.service.editoresYRevisores;

import BackendSIPTIS.commons.RespuestaServicio;

public interface DocenteTG2ProyectoService {

    RespuestaServicio obtenerTodosLosProyectosSinAceptarRevisadosPorIdDocente(Long id);

    RespuestaServicio obtenerTodosLosProyectosSinAceptarSinRevisarPorIdDocente(Long id);

    RespuestaServicio obtenerTodosLosProyectosAceptadosPorIdDocente(Long id);
}
