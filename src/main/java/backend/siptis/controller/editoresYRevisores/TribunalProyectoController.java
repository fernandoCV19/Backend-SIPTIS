package backend.siptis.controller.editoresYRevisores;

import backend.siptis.commons.MensajeServicio;
import backend.siptis.commons.RespuestaController;
import backend.siptis.commons.RespuestaServicio;
import BackendSIPTIS.service.editoresYRevisores.TribunalProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tribunal")
@RequiredArgsConstructor
public class TribunalProyectoController {

    private final TribunalProyectoService tribunalProyectoService;

    @GetMapping("/proyectosSinRevisar/{id}")
    public ResponseEntity<?> getProyectosSinRevisar(@PathVariable("id") Long id){
        RespuestaServicio respuestaServicio = tribunalProyectoService.obtenerTodosLosProyectosSinRevisarPorIdTribunal(id);
        return crearResponseEntityConProyectos(respuestaServicio);
    }

    @GetMapping("/proyectosRevisados/{id}")
    public ResponseEntity<?> getProyectoRevisados(@PathVariable("id") Long id){
        RespuestaServicio respuestaServicio = tribunalProyectoService.obtenerTodosLosProyectosRevisadosSinAceptarPorIdTribunal(id);
        return crearResponseEntityConProyectos(respuestaServicio);
    }

    @GetMapping("/proyectosPorDefender/{id}")
    public ResponseEntity<?> getProyectosAceptadosSinDefender(@PathVariable("id") Long id){
        RespuestaServicio respuestaServicio = tribunalProyectoService.obtenerTodosLosProyectosAceptadosSinDefenderPorIdTribunal(id);
        return crearResponseEntityConProyectos(respuestaServicio);
    }

    @GetMapping("/proyectosDefendidos/{id}")
    public ResponseEntity<?> getProyectosDefendidos(@PathVariable("id") Long id){
        RespuestaServicio respuestaServicio = tribunalProyectoService.obtenerTodosLosProyectosDefendidosPorIdTribunal(id);
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
