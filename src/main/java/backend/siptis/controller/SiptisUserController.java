package backend.siptis.controller;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.service.userData.UserAuthService;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class SiptisUserController {

    @Autowired
    private final UserAuthService userAuthService;


    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(
            @RequestBody StudentRegisterDTO estudianteDTO){

        ServiceAnswer estudiante = userAuthService.registerStudent(estudianteDTO);
        return crearResponseEntityRegistrar(estudiante);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(
            @RequestBody AdminRegisterDTO adminRegisterDTO){

        ServiceAnswer admin = userAuthService.registerAdmin(adminRegisterDTO);
        //return crearResponseEntityRegistrar(admin);
        return crearResponseEntityRegistrar(admin);
        /*return new ResponseEntity<>("Exito" +
                "", HttpStatus.OK);*/
    }

    @PostMapping("/test")
    public ResponseEntity<?> test(
            @RequestBody AdminRegisterDTO adminRegisterDTO){

        //RespuestaServicio admin = userAuthService.registerAdmin(adminRegisterDTO);
        return new ResponseEntity<>("hola", HttpStatus.OK);
    }



    private ResponseEntity<?> crearResponseEntityRegistrar(ServiceAnswer respuestaServicio){
        Object data = respuestaServicio.getData();
        ServiceMessage mensajeServicio = respuestaServicio.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if(mensajeServicio == ServiceMessage.ERROR_REGISTRO_CUENTA){
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        if(mensajeServicio == ServiceMessage.NOT_FOUND || mensajeServicio == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
