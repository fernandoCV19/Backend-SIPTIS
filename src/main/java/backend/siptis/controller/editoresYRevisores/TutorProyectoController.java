package backend.siptis.controller.editoresYRevisores;

import backend.siptis.commons.MensajeServicio;
import backend.siptis.commons.RespuestaController;
import backend.siptis.commons.RespuestaServicio;
import backend.siptis.service.editoresYRevisores.DocenteTG2ProyectoService;
import BackendSIPTIS.service.editoresYRevisores.TutorProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tutor")
@RequiredArgsConstructor
public class TutorProyectoController {

    private final TutorProyectoService tutorProyectoService;

    @GetMapping("/proyectosSinRevisar/{id}")
    public ResponseEntity<?> getProyectosSinRevisar(@PathVariable("id") Long id){
        RespuestaServicio respuestaServicio = tutorProyectoService.obtenerTodosLosProyectosSinAceptarSinRevisarPorIdTutor(id);
        return crearResponseEntityConProyectos(respuestaServicio);
    }

    @GetMapping("/proyectosRevisados/{id}")
    public ResponseEntity<?> getProyectoRevisados(@PathVariable("id") Long id){
        RespuestaServicio respuestaServicio = tutorProyectoService.obtenerTodosLosProyectosSinAceptarRevisadosPorIdTutor(id);
        return crearResponseEntityConProyectos(respuestaServicio);
    }

    @GetMapping("/proyectosAceptados/{id}")
    public ResponseEntity<?> getProyectosAceptados(@PathVariable("id") Long id){
        RespuestaServicio respuestaServicio = tutorProyectoService.obtenerTodosLosProyectosAceptadosPorIdTutor(id);
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
