package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.projectManagement.CloudManagementService;
import backend.siptis.service.projectManagement.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping ("/presentation")
public class PresentationController {
    @Autowired
    private CloudManagementService s3Service;

    @Autowired
    private PresentationService presentationService;

    @GetMapping("/test")
    String prueba() {
        return "Hola";
    }

    @PostMapping("/create")
    ResponseEntity<?> create(@RequestParam Long idProyecto, @RequestParam String fase){
        ServiceAnswer respuestaServicio = presentationService.createPresentation(idProyecto, fase);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @PostMapping("/grade")
    ResponseEntity<?> grade(@RequestParam Long idPresentacion){
        ServiceAnswer respuestaServicio = presentationService.gradePresentation(idPresentacion);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @GetMapping("/download-file/{folder}/{file}")
    ResponseEntity<?> downloadFile(@PathVariable String folder, @PathVariable String file){
        String key = folder+"/"+file;
        ServiceAnswer respuestaServicio = presentationService.downloadFileFromCloud(key);
        ByteArrayOutputStream downloadInputStream = (ByteArrayOutputStream) respuestaServicio.getData();
        return ResponseEntity.ok()
                .contentType(contentType(key))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + key + "\"")
                .body(downloadInputStream.toByteArray());
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

    private ResponseEntity<?> crearResponseEntityConPresentacion(ServiceAnswer respuestaServicio){
        Object data = respuestaServicio.getData();
        ServiceMessage mensajeServicio = respuestaServicio.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;


        if(mensajeServicio == ServiceMessage.NOT_FOUND || mensajeServicio == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    private MediaType contentType(String filename) {
        String[] fileArrSplit = filename.split("\\.");
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        return switch (fileExtension) {
            case "txt" -> MediaType.TEXT_PLAIN;
            case "png" -> MediaType.IMAGE_PNG;
            case "jpg" -> MediaType.IMAGE_JPEG;
            case "pdf" -> MediaType.APPLICATION_PDF;
            default -> MediaType.APPLICATION_OCTET_STREAM;
        };
    }


}
