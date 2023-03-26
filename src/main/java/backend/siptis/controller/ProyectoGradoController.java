package backend.siptis.controller;

import backend.siptis.commons.MensajeServicio;
import backend.siptis.commons.RespuestaController;
import backend.siptis.commons.RespuestaServicio;
import backend.siptis.service.ProyectoGradoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proyecto")
public class ProyectoGradoController {
    @Autowired
    public ProyectoGradoService proyectoGradoService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPresentaciones(@PathVariable ("id") Long idProyecto){
        RespuestaServicio respuestaServicio = proyectoGradoService.obtenerPresentaciones(idProyecto);
        return crearResponseEntity(respuestaServicio);
    }
    @GetMapping("/")
    public ResponseEntity<?> getProyectos(){
        RespuestaServicio respuestaServicio = proyectoGradoService.obtenerProyectos();
        return crearResponseEntity(respuestaServicio);
    }
    private ResponseEntity<?> crearResponseEntity(RespuestaServicio respuestaServicio){
        Object data = respuestaServicio.getData();
        MensajeServicio mensajeServicio = respuestaServicio.getMensajeServicio();
        HttpStatus httpStatus = HttpStatus.OK;

        if(mensajeServicio == MensajeServicio.NOT_FOUND || mensajeServicio == MensajeServicio.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        RespuestaController respuestaController = RespuestaController.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(respuestaController, httpStatus);
    }
}
