package BackendSIPTIS.service.editoresYRevisores;

import BackendSIPTIS.commons.RespuestaServicio;

public interface SupervisorProyectoService {

    RespuestaServicio obtenerTodosLosProyectosSinAceptarRevisadosPorIdSupervisor(Long id);

    RespuestaServicio obtenerTodosLosProyectosSinAceptarSinRevisarPorIdSupervisor(Long id);

    RespuestaServicio obtenerTodosLosProyectosAceptadosPorIdSupervisor(Long id);
}
