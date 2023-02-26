package BackendSIPTIS.service.editoresYRevisores;

import BackendSIPTIS.commons.MensajeServicio;
import BackendSIPTIS.commons.RespuestaServicio;
import BackendSIPTIS.model.entity.editoresYRevisores.DocenteTG2Proyecto;
import BackendSIPTIS.model.pjo.dto.gestionProyecto.ProyectoGradoMenuPrincipalDTO;
import BackendSIPTIS.model.repository.editoresYRevisores.SupervisorProyectoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupervisorProyectoServiceImpl implements SupervisorProyectoService{

    private final SupervisorProyectoRepository supervisorProyectoRepository;

    @Override
    public RespuestaServicio obtenerTodosLosProyectosSinAceptarRevisadosPorIdSupervisor(Long id) {
        List<DocenteTG2Proyecto> listaProyectos = supervisorProyectoRepository.findByUsuario_idAndAceptadoIsFalseAndRevisadoIsTrue(id);
        return obtenerProyectos(listaProyectos);
    }

    @Override
    public RespuestaServicio obtenerTodosLosProyectosSinAceptarSinRevisarPorIdSupervisor(Long id) {
        List<DocenteTG2Proyecto> listaProyectos = supervisorProyectoRepository.findByUsuario_idAndAceptadoIsFalseAndRevisadoIsFalse(id);
        return obtenerProyectos(listaProyectos);
    }

    @Override
    public RespuestaServicio obtenerTodosLosProyectosAceptadosPorIdSupervisor(Long id) {
        List<DocenteTG2Proyecto> listaProyectos = supervisorProyectoRepository.findByUsuario_idAndAceptadoIsTrue(id);
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
