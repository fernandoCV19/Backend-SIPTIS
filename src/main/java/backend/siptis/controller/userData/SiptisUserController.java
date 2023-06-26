package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.*;
import backend.siptis.model.pjo.dto.EditStudentInformationDTO;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import backend.siptis.service.userData.SiptisUserService;
import backend.siptis.service.userData.userAuthentication.UserAuthService;
import backend.siptis.service.userData.registerUser.RegisterUserService;
import backend.siptis.service.userData.searchUsers.SearchUsers;
import backend.siptis.service.userData.userPersonalInformation.AdminEditInformation;
import backend.siptis.service.userData.userPersonalInformation.UserEditInformation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
public class SiptisUserController {

    @Autowired
    private final UserAuthService userAuthService;

    @Autowired
    private final RegisterUserService registerUserService;

    @Autowired
    private final SiptisUserService userService;

    @Autowired
    private final AdminEditInformation adminEditInformationService;

    @Autowired
    private final UserEditInformation userEditInformationService;

    @Autowired
    private final SearchUsers searchUsersService;


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
        ServiceAnswer answer = adminEditInformationService.editStudentInformation(id, dto);
        return crearResponseEntityRegistrar(answer);
    }

    @PostMapping("/editTeacher/{userId}")
    public ResponseEntity<?> editTeacher(
            @PathVariable int userId,
            @RequestBody EditTeacherInformationDTO dto){

        Long id = Long.valueOf(userId);
        ServiceAnswer answer = adminEditInformationService.editTeacherInformation(id, dto);
        return crearResponseEntityRegistrar(answer);
    }

    /*@PostMapping("/editUser")
    public ResponseEntity<?> editMiInformation(
            @RequestBody StudentEditPersonalInfoDTO dto,
            @RequestHeader (name="Authorization") String token){

        Long id = userAuthService.getIdFromToken(token);

        ServiceAnswer answer = userEditInformationService.studentEditPersonalInfo(id, dto);
        return crearResponseEntityRegistrar(answer);
    }*/
    @GetMapping("/personal-activities/{userId}")
    public ResponseEntity<?> getPersonalProjectActivities(@PathVariable int userId){
        Long idL = Long.valueOf(userId);
        ServiceAnswer answer = userService.getPersonalActivities(idL);
        return crearResponseEntityRegistrar(answer);
    }

    @PostMapping("/editTeacher")
    public ResponseEntity<?> editMiInformationTeacher(
            @RequestBody TeacherEditPersonalInfoDTO dto,
            @RequestHeader (name="Authorization") String token){

        Long id = userAuthService.getIdFromToken(token);

        ServiceAnswer answer = userEditInformationService.teacherEditPersonalInfo(id, dto);
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
