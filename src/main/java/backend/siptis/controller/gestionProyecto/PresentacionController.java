package backend.siptis.controller.gestionProyecto;

import backend.siptis.commons.MensajeServicio;
import backend.siptis.commons.RespuestaController;
import backend.siptis.commons.RespuestaServicio;
import backend.siptis.service.gestionProyecto.CloudManagementService;
import backend.siptis.service.gestionProyecto.PresentacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping ("/presentation")
public class PresentacionController {
    @Autowired
    private CloudManagementService s3Service;

    @Autowired
    private PresentacionService presentacionService;

    @GetMapping("/test")
    String prueba() {
        return "Hola";
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestParam Long idProyecto, @RequestParam String fase){
        RespuestaServicio respuestaServicio = presentacionService.createPresentation(idProyecto, fase);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @PostMapping("/grade")
    ResponseEntity<?> grade(@RequestParam Long idPresentacion){
        RespuestaServicio respuestaServicio = presentacionService.gradePresentation(idPresentacion);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deletePresentation(@PathVariable("id") Long idPresentacion){
        RespuestaServicio respuestaServicio = presentacionService.delete(idPresentacion);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @PostMapping("/attach-file")
    ResponseEntity<?> attachFile (@RequestParam Long idPresentacion, @RequestParam MultipartFile file, @RequestParam String path) {
        RespuestaServicio respuestaServicio= presentacionService.attachFile(idPresentacion, file, path);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @DeleteMapping("/remove-file")
    ResponseEntity<?> removeFile (@RequestParam Long idPresentacion,  @RequestParam String path) {
        RespuestaServicio respuestaServicio= presentacionService.removeFile(idPresentacion, path);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    private ResponseEntity<?> crearResponseEntityConPresentacion(RespuestaServicio respuestaServicio){
        Object data = respuestaServicio.getData();
        MensajeServicio mensajeServicio = respuestaServicio.getMensajeServicio();
        HttpStatus httpStatus = HttpStatus.OK;


        if(mensajeServicio == MensajeServicio.NOT_FOUND || mensajeServicio == MensajeServicio.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        RespuestaController respuestaController = RespuestaController.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(respuestaController, httpStatus);
    }




}
