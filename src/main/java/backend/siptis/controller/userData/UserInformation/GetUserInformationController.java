package backend.siptis.controller.userData.UserInformation;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.UserEditPersonalInformationDTO;
import backend.siptis.service.userData.userAuthentication.UserAuthService;
import backend.siptis.service.userData.userPersonalInformation.EditPersonalInformationService;
import backend.siptis.service.userData.userPersonalInformation.GetPersonalInformationService;
import backend.siptis.service.userData.userPersonalInformation.PersonalInformation;
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
public class GetUserInformationController {

    @Autowired
    private final UserAuthService userAuthService;
    @Autowired
    private final GetPersonalInformationService getPersonalInformationService;


    @GetMapping("/personalInformation")
    public ResponseEntity<?> getInfo(@RequestHeader(name="Authorization") String token){

        Long id = userAuthService.getIdFromToken(token);
        //ServiceAnswer answerService = userAuthService.userInfo(id);
        ServiceAnswer answerService =
                getPersonalInformationService.getPersonalInformationById(id);

        return createResponse(answerService);
    }

    @GetMapping("/personalInformation/{userId}")
    public ResponseEntity<?> getInfoById(@PathVariable int userId){

        Long id = Long.valueOf(userId);
        //ServiceAnswer answerService = userAuthService.userInfo(id);
        ServiceAnswer answerService =
                getPersonalInformationService.getPersonalInformationById(id);

        return createResponse(answerService);
    }

    @GetMapping("/userCareer")
    public ResponseEntity<?> getCareer(@RequestHeader(name="Authorization") String token){

        Long id = userAuthService.getIdFromToken(token);
        //ServiceAnswer answerService = userAuthService.userInfo(id);
        ServiceAnswer answerService =
                getPersonalInformationService.getStudentCareerById(id);

        return createResponse(answerService);
    }

    @GetMapping("/userCareerById/{userId}")
    public ResponseEntity<?> getCareer(
            @PathVariable int userId){
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService =
                getPersonalInformationService.getStudentCareerById(id);

        return createResponse(answerService);
    }

    @GetMapping("/userAreas")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<?> getAreas(@RequestHeader(name="Authorization") String token){

        Long id = userAuthService.getIdFromToken(token);
        //ServiceAnswer answerService = userAuthService.userInfo(id);
        ServiceAnswer answerService =
                getPersonalInformationService.getTeacherAreasById(id);

        return createResponse(answerService);
    }

    @GetMapping("/userAreasById/{userId}")
    public ResponseEntity<?> getAreas(
            @PathVariable int userId){

        Long id = Long.valueOf(userId);
        ServiceAnswer answerService =
                getPersonalInformationService.getTeacherAreasById(id);

        return createResponse(answerService);
    }

    @GetMapping("/userNotSelectedAreas")
    public ResponseEntity<?> getNotSelectedAreas(
            @RequestHeader(name="Authorization") String token){

        Long id = userAuthService.getIdFromToken(token);
        //ServiceAnswer answerService = userAuthService.userInfo(id);
        ServiceAnswer answerService =
                getPersonalInformationService.getTeacherNotSelectedAreasById(id);

        return createResponse(answerService);
    }

    @GetMapping("/userNotSelectedAreasById/{userId}")
    public ResponseEntity<?> getNotSelectedAreasById(
            @PathVariable int userId){

        Long id = Long.valueOf(userId);
        //ServiceAnswer answerService = userAuthService.userInfo(id);
        ServiceAnswer answerService =
                getPersonalInformationService.getTeacherNotSelectedAreasById(id);

        return createResponse(answerService);
    }

    private ResponseEntity<?> createResponse(ServiceAnswer serviceAnswer){
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
