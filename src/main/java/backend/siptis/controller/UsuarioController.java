package backend.siptis.controller;

import backend.siptis.commons.MensajeServicio;
import backend.siptis.commons.RespuestaController;
import backend.siptis.commons.RespuestaServicio;
import backend.siptis.service.datosUsuario.UserAuthService;
import backend.siptis.model.pjo.dto.StudentInformationDTO;
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
public class UsuarioController {

    @Autowired
    private final UserAuthService userAuthService;




    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(
            @RequestBody StudentRegisterDTO estudianteDTO){

        RespuestaServicio estudiante = userAuthService.registerStudent(estudianteDTO);
        return crearResponseEntityRegistrar(estudiante);
    }


    private ResponseEntity<?> crearResponseEntityRegistrar(RespuestaServicio respuestaServicio){
        Object data = respuestaServicio.getData();
        MensajeServicio mensajeServicio = respuestaServicio.getMensajeServicio();
        HttpStatus httpStatus = HttpStatus.OK;

        if(mensajeServicio == MensajeServicio.NOT_FOUND || mensajeServicio == MensajeServicio.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        RespuestaController respuestaController = RespuestaController.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(respuestaController, httpStatus);
    }
}
