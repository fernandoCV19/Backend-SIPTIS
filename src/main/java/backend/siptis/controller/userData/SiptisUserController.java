package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.*;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import backend.siptis.service.userData.SiptisUserService;
import backend.siptis.service.userData.registerUser.RegisterUserService;
import backend.siptis.service.userData.searchUsers.SearchUsers;
import backend.siptis.service.userData.userAuthentication.UserAuthService;
import backend.siptis.service.userData.userPersonalInformation.AdminEditInformation;
import backend.siptis.service.userData.userPersonalInformation.UserEditInformation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class SiptisUserController {

    private final UserAuthService userAuthService;
    private final SiptisUserService userService;
    private final AdminEditInformation adminEditInformationService;
    private final UserEditInformation userEditInformationService;


    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LogInDTO logInDTO) {

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
    public ResponseEntity<?> getInfoId(@PathVariable int userId) {

        Long idL = Long.valueOf(userId);
        ServiceAnswer answerService = userAuthService.userInfo(idL);

        return crearResponseEntityRegistrar(answerService);
    }

    @GetMapping("/todos")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAll() {
        ServiceAnswer answerService = userService.findAll();
        return crearResponseEntityRegistrar(answerService);
    }

    @PostMapping("/editUser/{userId}")
    public ResponseEntity<?> editUser(
            @PathVariable int userId,
            @RequestBody EditStudentInformationDTO dto) {

        Long id = Long.valueOf(userId);
        ServiceAnswer answer = adminEditInformationService.editStudentInformation(id, dto);
        return crearResponseEntityRegistrar(answer);
    }

    @PostMapping("/editTeacher/{userId}")
    public ResponseEntity<?> editTeacher(
            @PathVariable int userId,
            @RequestBody EditTeacherInformationDTO dto) {

        Long id = Long.valueOf(userId);
        ServiceAnswer answer = adminEditInformationService.editTeacherInformation(id, dto);
        return crearResponseEntityRegistrar(answer);
    }


    @GetMapping("/personal-activities")
    public ResponseEntity<?> getPersonalProjectActivities(
            @RequestHeader(name = "Authorization") String token,
            Pageable pageable) {
        Long idL = userAuthService.getIdFromToken(token);
        ServiceAnswer answer = userService.getPersonalActivities(idL, pageable);
        return crearResponseEntityRegistrar(answer);
    }

    @PostMapping("/editTeacher")
    public ResponseEntity<?> editMiInformationTeacher(
            @RequestBody TeacherEditPersonalInfoDTO dto,
            @RequestHeader(name = "Authorization") String token) {

        Long id = userAuthService.getIdFromToken(token);

        ServiceAnswer answer = userEditInformationService.teacherEditPersonalInfo(id, dto);
        return crearResponseEntityRegistrar(answer);
    }

    private ResponseEntity<?> crearResponseEntityRegistrar(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if (messageService == ServiceMessage.OK) {
            httpStatus = HttpStatus.OK;
        }


        if (messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}