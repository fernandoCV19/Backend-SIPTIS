package backend.siptis.service.editoresYRevisores;

import backend.siptis.commons.RespuestaServicio;

public interface SupervisorProyectoService {

    RespuestaServicio obtenerTodosLosProyectosSinAceptarRevisadosPorIdSupervisor(Long id);

    RespuestaServicio obtenerTodosLosProyectosSinAceptarSinRevisarPorIdSupervisor(Long id);

    RespuestaServicio obtenerTodosLosProyectosAceptadosPorIdSupervisor(Long id);
}
