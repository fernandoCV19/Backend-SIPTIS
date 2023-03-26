package backend.siptis.service.editoresYRevisores;

import backend.siptis.commons.RespuestaServicio;

public interface TribunalProyectoService {

    RespuestaServicio obtenerTodosLosProyectosSinRevisarPorIdTribunal(Long id);

    RespuestaServicio obtenerTodosLosProyectosRevisadosSinAceptarPorIdTribunal(Long id);

    RespuestaServicio obtenerTodosLosProyectosAceptadosSinDefenderPorIdTribunal(Long id);

    RespuestaServicio obtenerTodosLosProyectosDefendidosPorIdTribunal(Long id);

}
