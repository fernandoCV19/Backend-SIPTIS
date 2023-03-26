package backend.siptis.service.editoresYRevisores;

import backend.siptis.commons.RespuestaServicio;

public interface TutorProyectoService {
    RespuestaServicio obtenerTodosLosProyectosSinAceptarRevisadosPorIdTutor(Long id);

    RespuestaServicio obtenerTodosLosProyectosSinAceptarSinRevisarPorIdTutor(Long id);

    RespuestaServicio obtenerTodosLosProyectosAceptadosPorIdTutor(Long id);
}
