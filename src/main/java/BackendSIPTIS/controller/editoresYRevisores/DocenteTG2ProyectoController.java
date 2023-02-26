package BackendSIPTIS.controller.editoresYRevisores;

import BackendSIPTIS.commons.MensajeServicio;
import BackendSIPTIS.commons.RespuestaController;
import BackendSIPTIS.commons.RespuestaServicio;
import BackendSIPTIS.service.editoresYRevisores.DocenteTG2ProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docente")
@RequiredArgsConstructor
public class DocenteTG2ProyectoController {

    private final DocenteTG2ProyectoService docenteTG2ProyectoService;

    @GetMapping("/proyectosSinRevisar/{id}")
    public ResponseEntity<?> getProyectosSinRevisar(@PathVariable("id") Long id){
        RespuestaServicio respuestaServicio = docenteTG2ProyectoService.obtenerTodosLosProyectosSinAceptarSinRevisarPorIdDocente(id);
        return crearResponseEntityConProyectos(respuestaServicio);
    }

    @GetMapping("/proyectosRevisados/{id}")
    public ResponseEntity<?> getProyectoRevisados(@PathVariable("id") Long id){
        RespuestaServicio respuestaServicio = docenteTG2ProyectoService.obtenerTodosLosProyectosSinAceptarRevisadosPorIdDocente(id);
        return crearResponseEntityConProyectos(respuestaServicio);
    }

    @GetMapping("/proyectosAceptados/{id}")
    public ResponseEntity<?> getProyectosAceptados(@PathVariable("id") Long id){
        RespuestaServicio respuestaServicio = docenteTG2ProyectoService.obtenerTodosLosProyectosAceptadosPorIdDocente(id);
        return crearResponseEntityConProyectos(respuestaServicio);
    }

    private ResponseEntity<?> crearResponseEntityConProyectos(RespuestaServicio respuestaServicio){
        Object data = respuestaServicio.getData();
        MensajeServicio mensajeServicio = respuestaServicio.getMensajeServicio();
        HttpStatus httpStatus = HttpStatus.OK;

        if(mensajeServicio == MensajeServicio.ID_NO_EXISTE)
            httpStatus = HttpStatus.NOT_FOUND;

        RespuestaController respuestaController = RespuestaController.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(respuestaController, httpStatus);
    }
}
