package BackendSIPTIS.service.editoresYRevisores;

import BackendSIPTIS.commons.MensajeServicio;
import BackendSIPTIS.commons.RespuestaServicio;
import BackendSIPTIS.model.entity.editoresYRevisores.DocenteTG2Proyecto;
import BackendSIPTIS.model.pjo.dto.gestionProyecto.ProyectoGradoMenuPrincipalDTO;
import BackendSIPTIS.model.repository.editoresYRevisores.DocenteTG2ProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocenteTG2ProyectoServiceImpl implements DocenteTG2ProyectoService{

    private final DocenteTG2ProyectoRepository docenteTG2ProyectoRepository;

    @Override
    public RespuestaServicio obtenerTodosLosProyectosSinAceptarRevisadosPorIdDocente(Long id) {
        if(docenteTG2ProyectoRepository.findById(id).isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ID_NO_EXISTE).data(null).build();
        }

        List<DocenteTG2Proyecto> listaProyectos = docenteTG2ProyectoRepository.findByDocenteAndAceptadoIsFalseAndRevisadoIsTrue(id);
        return obtenerProyectos(listaProyectos);
    }


    @Override
    public RespuestaServicio obtenerTodosLosProyectosSinAceptarSinRevisarPorIdDocente(Long id) {
        if(docenteTG2ProyectoRepository.findById(id).isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ID_NO_EXISTE).data(null).build();
        }

        List<DocenteTG2Proyecto> listaProyectos = docenteTG2ProyectoRepository.findByDocenteAndAceptadoIsFalseAndRevisadoIsFalse(id);
        return obtenerProyectos(listaProyectos);
    }

    @Override
    public RespuestaServicio obtenerTodosLosProyectosAceptadosPorIdDocente(Long id) {
        if(docenteTG2ProyectoRepository.findById(id).isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.ID_NO_EXISTE).data(null).build();
        }

        List<DocenteTG2Proyecto> listaProyectos = docenteTG2ProyectoRepository.findByDocenteAndAceptadoIsTrue(id);
        return obtenerProyectos(listaProyectos);
    }

    private RespuestaServicio obtenerProyectos(List<DocenteTG2Proyecto> listaProyectos){
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
