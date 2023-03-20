package BackendSIPTIS.controller;

import java.util.List;

import BackendSIPTIS.model.repository.ProyectoDeGradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import BackendSIPTIS.commons.MensajeServicio;
import BackendSIPTIS.commons.RespuestaController;
import BackendSIPTIS.commons.RespuestaServicio;
import BackendSIPTIS.model.entity.gestionProyecto.Presentacion;
import BackendSIPTIS.service.CloudManagementService;
import BackendSIPTIS.service.PresentacionService;

@RestController
@RequestMapping ("/presentacion")
public class PresentacionController {
    @Autowired
    private CloudManagementService s3Service;

    @Autowired
    private PresentacionService presentacionService;

    @GetMapping("/get")
    String prueba() {
        return "hola";
    }

    @GetMapping("/crear/{idProyecto}")
    ResponseEntity<?> create(@PathVariable("idProyecto") Long idProyecto){
        RespuestaServicio respuestaServicio = presentacionService.generateNew(idProyecto);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePresentacion(@PathVariable("id") Long idPresentacion){
        RespuestaServicio respuestaServicio = presentacionService.delete(idPresentacion);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @PostMapping("/enviar")
    ResponseEntity<?> entregar( @RequestParam Long idPresentacion, @RequestParam String fase) {
        RespuestaServicio respuestaServicio= presentacionService.entregarPresentacion(idPresentacion, fase);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @PostMapping("/libro")
    ResponseEntity<?> subirLibro(@RequestParam Long idPresentacion, @RequestParam MultipartFile libroAzul) {
        RespuestaServicio respuestaServicio= presentacionService.subirLibroAzul(idPresentacion, libroAzul);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @PostMapping("/trabajo")
    ResponseEntity<?> subirTrabajo(@RequestParam Long idPresentacion, @RequestParam MultipartFile trabajo) {
        RespuestaServicio respuestaServicio= presentacionService.subirProyecto(idPresentacion, trabajo);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @DeleteMapping("/libro/{id}")
    ResponseEntity<?> quitarLibro(@PathVariable("id") Long idPresentacion){
        RespuestaServicio respuestaServicio = presentacionService.quitarLibroAzul(idPresentacion);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }
    @DeleteMapping("/trabajo/{id}")
    ResponseEntity<?> quitarProyecto(@PathVariable("id") Long idPresentacion){
        RespuestaServicio respuestaServicio = presentacionService.quitarTrabajoGrado(idPresentacion);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }


    //@PostMapping("/subir")
    //ResponseEntity<?> upload(@RequestParam MultipartFile libroAzul, @RequestParam MultipartFile proyecto, @RequestParam String fase, @RequestParam Long proyecto_id) {
        //RespuestaServicio respuestaServicio= presentacionService.createPresentacion(libroAzul, proyecto, fase, proyecto_id);
        //return null;
    //}



    private ResponseEntity<?> crearResponseEntityConPresentacion(RespuestaServicio respuestaServicio){
        Object data = respuestaServicio.getData();
        MensajeServicio mensajeServicio = respuestaServicio.getMensajeServicio();
        HttpStatus httpStatus = HttpStatus.OK;

        if (mensajeServicio == MensajeServicio.PRESENTACION_PENDIENTE){
            RespuestaController respuestaController = RespuestaController.builder().data(data).message(mensajeServicio.toString()).build();
            return new ResponseEntity<>(respuestaController, httpStatus);
        }

        if(mensajeServicio == MensajeServicio.NOT_FOUND || mensajeServicio == MensajeServicio.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        RespuestaController respuestaController = RespuestaController.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(respuestaController, httpStatus);
    }




}
