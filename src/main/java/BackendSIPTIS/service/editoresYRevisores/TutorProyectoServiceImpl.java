package BackendSIPTIS.service.editoresYRevisores;

import BackendSIPTIS.commons.MensajeServicio;
import BackendSIPTIS.commons.RespuestaServicio;
import BackendSIPTIS.model.entity.editoresYRevisores.TutorProyecto;
import BackendSIPTIS.model.pjo.dto.gestionProyecto.ProyectoGradoMenuPrincipalDTO;
import BackendSIPTIS.model.repository.editoresYRevisores.TutorProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TutorProyectoServiceImpl implements TutorProyectoService{

    private final TutorProyectoRepository tutorProyectoRepository;

    @Override
    public RespuestaServicio obtenerTodosLosProyectosSinAceptarRevisadosPorIdTutor(Long id) {
        if(tutorProyectoRepository.findById(id).isEmpty()) {
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ID_NO_EXISTE).data(null).build();
        }

        List<TutorProyecto> listaProyectos = tutorProyectoRepository.findByTutorAndAceptadoIsFalseAndRevisadoIsTrue(id);
        return obtenerProyectos(listaProyectos);
    }

    @Override
    public RespuestaServicio obtenerTodosLosProyectosSinAceptarSinRevisarPorIdTutor(Long id) {
        if(tutorProyectoRepository.findById(id).isEmpty()) {
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ID_NO_EXISTE).data(null).build();
        }

        List<TutorProyecto> listaProyectos = tutorProyectoRepository.findByTutorAndAceptadoIsFalseAndRevisadoIsFalse(id);
        return obtenerProyectos(listaProyectos);
    }

    @Override
    public RespuestaServicio obtenerTodosLosProyectosAceptadosPorIdTutor(Long id) {
        if(tutorProyectoRepository.findById(id).isEmpty()) {
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ID_NO_EXISTE).data(null).build();
        }

        List<TutorProyecto> listaProyectos = tutorProyectoRepository.findByTutorAndAceptadoIsTrue(id);
        return obtenerProyectos(listaProyectos);
    }

    private RespuestaServicio obtenerProyectos(List<TutorProyecto> listaProyectos){
        if(listaProyectos.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.SIN_PROYECTOS).data(listaProyectos).build();
        }

        List<ProyectoGradoMenuPrincipalDTO> data = listaProyectos
                .stream()
                .map(aux -> new ProyectoGradoMenuPrincipalDTO(aux.getProyecto(), aux.getAceptado(), aux.getRevisado()))
                .collect(Collectors.toList());

        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OK).data(data).build();
    }
}