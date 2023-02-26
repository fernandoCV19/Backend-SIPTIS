package BackendSIPTIS.service.editoresYRevisores;

import BackendSIPTIS.commons.RespuestaServicio;

public interface TribunalProyectoService {

    RespuestaServicio obtenerTodosLosProyectosSinRevisarPorIdTribunal(Long id);

    RespuestaServicio obtenerTodosLosProyectosRevisadosSinAceptarPorIdTribunal(Long id);

    RespuestaServicio obtenerTodosLosProyectosAceptadosSinDefenderPorIdTribunal(Long id);

    RespuestaServicio obtenerTodosLosProyectosDefendidosPorIdTribunal(Long id);

}
