package BackendSIPTIS.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/subir")
    ResponseEntity<?> upload(@RequestParam MultipartFile libroAzul, @RequestParam MultipartFile proyecto, @RequestParam String fase, @RequestParam Long proyecto_id) {
        RespuestaServicio respuestaServicio= presentacionService.createPresentacion(libroAzul, proyecto, fase, proyecto_id);
        //ResponseEntity <Presentacion> respuesta = new ResponseEntity<> ((Presentacion)respuestaServicio.getData(), HttpStatus.OK);
        return crearResponseEntityConPresentacion(respuestaServicio);
    }

    @GetMapping(value = "/get-object", params = "key")
    ResponseEntity<String> getObject(@RequestParam String key) {
        String content = s3Service.getObject(key);

        return (ResponseEntity<String>) ResponseEntity
                .ok()
                .body(content);

    }

    @DeleteMapping(value = "/delete-object", params = "key")
    void deleteObject(@RequestParam String key) {
        s3Service.deleteObject(key);
    }

    @PostMapping("/upload-object")
    void  uploadObject(@RequestParam MultipartFile file, @RequestParam String key) {
    
    }

    @GetMapping(value = "/get-object-list", params = "folder")
    ResponseEntity<Object> getObjectList(@RequestParam String folder) {
        List <String> content = s3Service.getObjectslistFromFolder(folder);

        return new ResponseEntity<Object>(content, HttpStatus.OK);

    }

    private ResponseEntity<?> crearResponseEntityConPresentacion(RespuestaServicio respuestaServicio){
        Object data = respuestaServicio.getData();
        System.out.println(data.getClass());
        MensajeServicio mensajeServicio = respuestaServicio.getMensajeServicio();
        HttpStatus httpStatus = HttpStatus.OK;

        if(mensajeServicio == MensajeServicio.NOT_FOUND)
            httpStatus = HttpStatus.NOT_FOUND;

        RespuestaController respuestaController = RespuestaController.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(respuestaController, httpStatus);
    }


}
