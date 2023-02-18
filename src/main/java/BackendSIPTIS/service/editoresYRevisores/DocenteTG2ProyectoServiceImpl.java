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
    public RespuestaServicio obtenerTodosLosProyectosSinAceptarPorIdDocente(Long id) {
        List<DocenteTG2Proyecto> listaProyectos = docenteTG2ProyectoRepository.findByUsuario_idAndAceptadoIsFalse(id);
        if(listaProyectos.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.SIN_PROYECTOS).data(listaProyectos).build();
        }

        List<ProyectoGradoMenuPrincipalDTO> data = listaProyectos
                .stream()
                .map(aux -> new ProyectoGradoMenuPrincipalDTO(aux.getProyecto(), false, aux.getRevisado()))
                .collect(Collectors.toList());

        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OK).data(data).build();    }

    @Override
    public RespuestaServicio obtenerTodosLosProyectosAceptadosPorIdDocente(Long id) {
        List<DocenteTG2Proyecto> listaProyectos = docenteTG2ProyectoRepository.findByUsuario_idAndAceptadoIsTrue(id);
        if(listaProyectos.isEmpty()){
            return RespuestaServicio.builder().mensajeServicio(MensajeServicio.SIN_PROYECTOS).data(listaProyectos).build();
        }

        List<ProyectoGradoMenuPrincipalDTO> data = listaProyectos
                .stream()
                .map(aux -> new ProyectoGradoMenuPrincipalDTO(aux.getProyecto(), true, aux.getRevisado()))
                .collect(Collectors.toList());

        return RespuestaServicio.builder().mensajeServicio(MensajeServicio.OK).data(data).build();
    }
}
