package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.Phase;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.projectManagement.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping ("/presentation")
@CrossOrigin
public class PresentationController {

    @Autowired
    private PresentationService presentationService;

    @GetMapping("/test")
    String prueba() {
        return "Hola";
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestParam Long idProyecto, @RequestParam Phase fase){
        ServiceAnswer respuestaServicio = presentationService.createPresentation(idProyecto, fase);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @PostMapping("/grade")
    ResponseEntity<?> grade(@RequestParam Long idPresentacion){
        ServiceAnswer respuestaServicio = presentationService.gradePresentation(idPresentacion);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePresentation(@PathVariable("id") Long idPresentacion){
        ServiceAnswer respuestaServicio = presentationService.delete(idPresentacion);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @PostMapping("/attach-file")
    ResponseEntity<?> attachFile (@RequestParam Long idPresentacion, @RequestParam MultipartFile file, @RequestParam String path) {
        ServiceAnswer respuestaServicio= presentationService.attachFile(idPresentacion, file, path);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @DeleteMapping("/remove-file")
    ResponseEntity<?> removeFile (@RequestParam Long idPresentacion,  @RequestParam String path) {
        ServiceAnswer respuestaServicio= presentationService.removeFile(idPresentacion, path);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @GetMapping("/getLastReviews/{id}")
    ResponseEntity<?> getLastReviews(@PathVariable("id") Long projectId){
        ServiceAnswer serviceAnswer = presentationService.getLastReviewsFromAPresentation(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if(serviceAnswer.getServiceMessage() != ServiceMessage.OK){
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    private ResponseEntity<?> crearResponseEntityConPresentacion(ServiceAnswer respuestaServicio){
        Object data = respuestaServicio.getData();
        ServiceMessage mensajeServicio = respuestaServicio.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;


        if(mensajeServicio == ServiceMessage.NOT_FOUND || mensajeServicio == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }




}
