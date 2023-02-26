package BackendSIPTIS.service.editoresYRevisores;

import BackendSIPTIS.commons.RespuestaServicio;

public interface TutorProyectoService {
    RespuestaServicio obtenerTodosLosProyectosSinAceptarRevisadosPorIdTutor(Long id);

    RespuestaServicio obtenerTodosLosProyectosSinAceptarSinRevisarPorIdTutor(Long id);

    RespuestaServicio obtenerTodosLosProyectosAceptadosPorIdTutor(Long id);
}
