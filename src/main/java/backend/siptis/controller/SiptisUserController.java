package backend.siptis.controller;

import backend.siptis.auth.jwt.JWTokenUtils;
import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.records.LogInDTO;
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


    @PostMapping("/register/student")
    public ResponseEntity<?> registerStudent(
            @RequestBody StudentRegisterDTO studentDTO){

        ServiceAnswer student = userAuthService.registerStudent(studentDTO);
        return crearResponseEntityRegistrar(student);
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


    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LogInDTO logInDTO){

        ServiceAnswer answerService = userAuthService.logIn(logInDTO);

        return crearResponseEntityRegistrar(answerService);
    }

    @GetMapping("/information")
    public ResponseEntity<?> getInfo(){

        Long id = 1L;
        ServiceAnswer answerService = userAuthService.userInfo(2L);

        return crearResponseEntityRegistrar(answerService);
    }

    private ResponseEntity<?> crearResponseEntityRegistrar(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if(messageService == ServiceMessage.OK){
            httpStatus = HttpStatus.OK;
        }


        if(messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
