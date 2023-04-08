package backend.siptis.controller;

import backend.siptis.auth.jwt.JWTokenUtils;
import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.service.userData.UserAuthService;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.service.userData.UserDetailImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class SiptisUserController {

    @Autowired
    private final UserAuthService userAuthService;
    private final AuthenticationManager authenticationManager;
    private final JWTokenUtils jwTokenUtils;


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

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AdminRegisterDTO adminRegisterDTO){

        System.out.println("entra al controller");
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(adminRegisterDTO.getEmail(),adminRegisterDTO.getPassword())
            );

            if (auth.isAuthenticated()){
                UserDetailImp userDetails= (UserDetailImp) auth.getPrincipal();
                String token = JWTokenUtils.createToken(userDetails);
                System.out.println("token: "+token);
                return new ResponseEntity<>(token, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("error al registrar", HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            System.out.println("Error de autenticacion: "+e.getMessage());
            return new ResponseEntity<>("Error de autenticacion: "+e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        //RespuestaServicio admin = userAuthService.registerAdmin(adminRegisterDTO);
        //return new ResponseEntity<>("hola", HttpStatus.OK);
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
