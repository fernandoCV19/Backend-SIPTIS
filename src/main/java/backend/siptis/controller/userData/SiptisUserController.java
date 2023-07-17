package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.*;
import backend.siptis.model.pjo.dto.records.LogInDTO;
import backend.siptis.service.userData.SiptisUserService;
import backend.siptis.service.userData.UserInformationService;
import backend.siptis.service.userData.userPersonalInformation.AdminEditInformation;
import backend.siptis.service.userData.userPersonalInformation.UserEditInformation;
import lombok.RequiredArgsConstructor;
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

    private final SiptisUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LogInDTO logInDTO) {
        ServiceAnswer answerService = userService.logIn(logInDTO);
        return createResponseEntity(answerService);
    }


    @GetMapping("/personalInformation/{userId}")
    public ResponseEntity<?> getInfoById(@PathVariable int userId){

        Long id = Long.valueOf(userId);
        /*ServiceAnswer answerService =
                userService.getUserPersonalInformation(id);

        return createResponseEntity(answerService);*/
        return null;
    }

    @GetMapping("/information")
    public ResponseEntity<?> getInfo(@RequestHeader (name="Authorization") String token){

        Long id = userService.getIdFromToken(token);
        //ServiceAnswer answerService = userInformationService(id);

        //return crearResponseEntityRegistrar(answerService);

        return null;
    }


    @GetMapping("/personal-activities")
    public ResponseEntity<?> getPersonalProjectActivities(
            @RequestHeader(name = "Authorization") String token,
            Pageable pageable) {
        Long idL = userService.getIdFromToken(token);
        ServiceAnswer answer = userService.getPersonalActivities(idL, pageable);
        return createResponseEntity(answer);
    }


    @GetMapping("/userCareer")
    public ResponseEntity<?> getCareer(@RequestHeader(name="Authorization") String token){

        Long id = userService.getIdFromToken(token);
        ServiceAnswer answerService =
                userService.getStudentCareerById(id);

        return createResponseEntity(answerService);
    }

    @GetMapping("/userCareerById/{userId}")
    public ResponseEntity<?> getCareer(
            @PathVariable int userId){
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService =
                userService.getStudentCareerById(id);

        return createResponseEntity(answerService);
    }

    @GetMapping("/userAreas")
    //@PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<?> getAreas(@RequestHeader(name="Authorization") String token){

        Long id = userService.getIdFromToken(token);
        ServiceAnswer answerService = userService.getTeacherAreasById(id);

        return createResponseEntity(answerService);
    }

    @GetMapping("/userAreasById/{userId}")
    public ResponseEntity<?> getAreas(
            @PathVariable int userId){

        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = userService.getTeacherAreasById(id);

        return createResponseEntity(answerService);
    }

    @GetMapping("/userNotSelectedAreas")
    public ResponseEntity<?> getNotSelectedAreas(
            @RequestHeader(name="Authorization") String token){

        Long id = userService.getIdFromToken(token);
        //ServiceAnswer answerService = userAuthService.userInfo(id);
        ServiceAnswer answerService =
                userService.getTeacherNotSelectedAreasById(id);

        return createResponseEntity(answerService);
    }

    @GetMapping("/userNotSelectedAreasById/{userId}")
    public ResponseEntity<?> getNotSelectedAreasById(
            @PathVariable int userId){

        Long id = Long.valueOf(userId);
        //ServiceAnswer answerService = userAuthService.userInfo(id);
        ServiceAnswer answerService =
                userService.getTeacherNotSelectedAreasById(id);

        return createResponseEntity(answerService);
    }


    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage mensajeServicio = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if(mensajeServicio == ServiceMessage.NOT_FOUND || mensajeServicio == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

/*


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


    @PostMapping("/editTeacher")
    public ResponseEntity<?> editMiInformationTeacher(
            @RequestBody TeacherEditPersonalInfoDTO dto,
            @RequestHeader(name = "Authorization") String token) {

        Long id = userService.getIdFromToken(token);

        ServiceAnswer answer = userEditInformationService.teacherEditPersonalInfo(id, dto);
        return crearResponseEntityRegistrar(answer);
    }

    */
}