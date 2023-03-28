package backend.siptis.controller;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.RespuestaController;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.CloudManagementService;
import backend.siptis.service.PresentacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        ServiceAnswer serviceAnswer = presentacionService.generateNew(idProyecto);
        return crearResponseEntityConPresentacion(serviceAnswer);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePresentacion(@PathVariable("id") Long idPresentacion){
        ServiceAnswer serviceAnswer = presentacionService.delete(idPresentacion);
        return crearResponseEntityConPresentacion(serviceAnswer);
    }

    @PostMapping("/enviar")
    ResponseEntity<?> entregar( @RequestParam Long idPresentacion, @RequestParam String fase) {
        ServiceAnswer serviceAnswer = presentacionService.entregarPresentacion(idPresentacion, fase);
        return crearResponseEntityConPresentacion(serviceAnswer);
    }

    @PostMapping("/libro")
    ResponseEntity<?> subirLibro(@RequestParam Long idPresentacion, @RequestParam MultipartFile libroAzul) {
        ServiceAnswer serviceAnswer = presentacionService.subirLibroAzul(idPresentacion, libroAzul);
        return crearResponseEntityConPresentacion(serviceAnswer);
    }

    @PostMapping("/trabajo")
    ResponseEntity<?> subirTrabajo(@RequestParam Long idPresentacion, @RequestParam MultipartFile trabajo) {
        ServiceAnswer serviceAnswer = presentacionService.subirProyecto(idPresentacion, trabajo);
        return crearResponseEntityConPresentacion(serviceAnswer);
    }

    @DeleteMapping("/libro/{id}")
    ResponseEntity<?> quitarLibro(@PathVariable("id") Long idPresentacion){
        ServiceAnswer serviceAnswer = presentacionService.quitarLibroAzul(idPresentacion);
        return crearResponseEntityConPresentacion(serviceAnswer);
    }
    @DeleteMapping("/trabajo/{id}")
    ResponseEntity<?> quitarProyecto(@PathVariable("id") Long idPresentacion){
        ServiceAnswer serviceAnswer = presentacionService.quitarTrabajoGrado(idPresentacion);
        return crearResponseEntityConPresentacion(serviceAnswer);
    }


    //@PostMapping("/subir")
    //ResponseEntity<?> upload(@RequestParam MultipartFile libroAzul, @RequestParam MultipartFile proyecto, @RequestParam String fase, @RequestParam Long proyecto_id) {
        //RespuestaServicio respuestaServicio= presentacionService.createPresentacion(libroAzul, proyecto, fase, proyecto_id);
        //return null;
    //}



    private ResponseEntity<?> crearResponseEntityConPresentacion(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if (serviceMessage == ServiceMessage.PRESENTACION_PENDIENTE){
            RespuestaController respuestaController = RespuestaController.builder().data(data).message(serviceMessage.toString()).build();
            return new ResponseEntity<>(respuestaController, httpStatus);
        }

        if(serviceMessage == ServiceMessage.NOT_FOUND || serviceMessage == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        RespuestaController respuestaController = RespuestaController.builder().data(data).message(serviceMessage.toString()).build();
        return new ResponseEntity<>(respuestaController, httpStatus);
    }




}
