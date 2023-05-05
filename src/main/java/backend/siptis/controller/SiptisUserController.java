package backend.siptis.controller;

import backend.siptis.auth.jwt.JWTokenUtils;
import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.records.Activity;
import backend.siptis.model.pjo.dto.AdminRegisterDTO;
import backend.siptis.model.pjo.dto.EditStudentInformationDTO;
import backend.siptis.model.pjo.dto.StudentEditPersonalInfoDTO;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import backend.siptis.service.userData.SiptisUserService;
import backend.siptis.service.userData.UserAuthService;
import backend.siptis.model.pjo.dto.StudentRegisterDTO;
import backend.siptis.service.userData.UserDetailImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
public class SiptisUserController {

    @Autowired
    private final UserAuthService userAuthService;

    @Autowired
    private final SiptisUserService userService;



    @PostMapping("/register/student")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerStudent(
            @RequestBody StudentRegisterDTO studentDTO){

        ServiceAnswer student = userAuthService.registerStudent(studentDTO);
        return crearResponseEntityRegistrar(student);
    }

    @PostMapping("/register/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
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

    public ResponseEntity<?> getInfo(@RequestHeader (name="Authorization") String token){

        Long id = userAuthService.getIdFromToken(token);
        ServiceAnswer answerService = userAuthService.userInfo(id);

        return crearResponseEntityRegistrar(answerService);
    }

    @GetMapping("/information/{userId}")
    public ResponseEntity<?> getInfoId(@PathVariable int userId){

        Long idL = Long.valueOf(userId);
        ServiceAnswer answerService = userAuthService.userInfo(idL);

        return crearResponseEntityRegistrar(answerService);
    }

    @GetMapping("/todos")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAll(){
        ServiceAnswer answerService = userService.findAll();
        return crearResponseEntityRegistrar(answerService);
    }

    @PostMapping("/editUser/{userId}")
    public ResponseEntity<?> editUser(
            @PathVariable int userId,
            @RequestBody EditStudentInformationDTO dto){

        Long id = Long.valueOf(userId);
        ServiceAnswer answer = userService.editStudentInformation(id, dto);
        return crearResponseEntityRegistrar(answer);
    }

        @PostMapping("/editUser")
    public ResponseEntity<?> editMiInformation(
            @RequestBody StudentEditPersonalInfoDTO dto,
            @RequestHeader (name="Authorization") String token){

        Long id = userAuthService.getIdFromToken(token);

        ServiceAnswer answer = userService.studentEditPersonalInfo(id, dto);
        return crearResponseEntityRegistrar(answer);
    }
    @GetMapping("/personal-activities/{userId}")
    public ResponseEntity<?> getPersonalProjectActivities(@PathVariable int userId){
        Long idL = Long.valueOf(userId);
        ServiceAnswer answer = userService.getPersonalActivities(idL);
        return crearResponseEntityRegistrar(answer);
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
