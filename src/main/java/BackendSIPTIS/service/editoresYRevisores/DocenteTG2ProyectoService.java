package BackendSIPTIS.service.editoresYRevisores;

import BackendSIPTIS.commons.RespuestaServicio;

public interface DocenteTG2ProyectoService {

    RespuestaServicio obtenerTodosLosProyectosSinAceptarPorIdDocente(Long id);

    RespuestaServicio obtenerTodosLosProyectosAceptadosPorIdDocente(Long id);
}
