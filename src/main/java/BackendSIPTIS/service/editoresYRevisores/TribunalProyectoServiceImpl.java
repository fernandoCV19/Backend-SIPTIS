package BackendSIPTIS.service.editoresYRevisores;

import BackendSIPTIS.commons.MensajeServicio;
import BackendSIPTIS.commons.RespuestaServicio;
import BackendSIPTIS.model.entity.editoresYRevisores.TribunalProyecto;
import BackendSIPTIS.model.pjo.dto.gestionProyecto.ProyectoGradoMenuPrincipalFaseTribunalesDTO;
import BackendSIPTIS.model.repository.editoresYRevisores.TribunalProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TribunalProyectoServiceImpl implements TribunalProyectoService{

    private final TribunalProyectoRepository tribunalProyectoRepository;

    @Override
    public RespuestaServicio obtenerTodosLosProyectosSinRevisarPorIdTribunal(Long id) {
        if(tribunalProyectoRepository.findById(id).isEmpty()) {
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ID_NO_EXISTE).data(null).build();
        }

        List<TribunalProyecto> listaProyectos = tribunalProyectoRepository.findByUsuario_idAndAceptadoIsFalseAndRevisadoIsFalse(id);

        return obtenerProyectos(listaProyectos);
    }

    @Override
    public RespuestaServicio obtenerTodosLosProyectosRevisadosSinAceptarPorIdTribunal(Long id) {
        if(tribunalProyectoRepository.findById(id).isEmpty()) {
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ID_NO_EXISTE).data(null).build();
        }

        List<TribunalProyecto> listaProyectos = tribunalProyectoRepository.findByUsuario_idAndAceptadoIsFalseAndRevisadoIsTrue(id);

        return obtenerProyectos(listaProyectos);
    }

    @Override
    public RespuestaServicio obtenerTodosLosProyectosAceptadosSinDefenderPorIdTribunal(Long id) {
        if(tribunalProyectoRepository.findById(id).isEmpty()) {
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ID_NO_EXISTE).data(null).build();
        }

        List<TribunalProyecto> listaProyectos = tribunalProyectoRepository.findByUsuario_idAndAceptadoIsTrueAndNotaDefensaIsNull(id);

        return obtenerProyectos(listaProyectos);
    }

    @Override
    public RespuestaServicio obtenerTodosLosProyectosDefendidosPorIdTribunal(Long id) {
        if(tribunalProyectoRepository.findById(id).isEmpty()) {
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ID_NO_EXISTE).data(null).build();
        }

        List<TribunalProyecto> listaProyectos = tribunalProyectoRepository.findByUsuario_idAndNotaDefensaIsNotNull(id);

        return obtenerProyectos(listaProyectos);
    }

    private RespuestaServicio obtenerProyectos(List<TribunalProyecto> listaProyectos){
        if(listaProyectos.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.SIN_PROYECTOS).data(listaProyectos).build();
        }

        List<ProyectoGradoMenuPrincipalFaseTribunalesDTO> data = listaProyectos
                .stream()
                .map(aux -> new ProyectoGradoMenuPrincipalFaseTribunalesDTO(aux.getProyecto(), aux.getNotaDefensa(), aux.getAceptado(), aux.getRevisado()))
                .collect(Collectors.toList());

        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OK).data(data).build();
    }
}
