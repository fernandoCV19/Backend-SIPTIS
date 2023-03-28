package backend.siptis.service.gestionProyecto;

import backend.siptis.commons.RespuestaServicio;

public interface ProyectoGradoService {

    RespuestaServicio obtenerProyectos();

    RespuestaServicio obtenerPresentaciones (Long idProyecto);

}
