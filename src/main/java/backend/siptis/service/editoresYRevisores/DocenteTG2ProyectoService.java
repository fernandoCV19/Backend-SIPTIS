package backend.siptis.service.editoresYRevisores;

import backend.siptis.commons.RespuestaServicio;

public interface DocenteTG2ProyectoService {

    RespuestaServicio obtenerTodosLosProyectosSinAceptarRevisadosPorIdDocente(Long id);

    RespuestaServicio obtenerTodosLosProyectosSinAceptarSinRevisarPorIdDocente(Long id);

    RespuestaServicio obtenerTodosLosProyectosAceptadosPorIdDocente(Long id);
}
