package backend.siptis.controller.userData.UserInformation;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.UserEditPersonalInformationDTO;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;
import backend.siptis.service.userData.userAuthentication.UserAuthService;
import backend.siptis.service.userData.userPersonalInformation.EditPersonalInformationService;
import backend.siptis.service.userData.userPersonalInformation.PersonalInformation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
public class UpdateUserInformationController {

    @Autowired
    private final UserAuthService userAuthService;
    @Autowired
    private  final PersonalInformation personalInformationService;
    @Autowired
    private  final EditPersonalInformationService editPersonalInformationService;


    @PostMapping("/updateAreas")
    public ResponseEntity<?> setSelectedAreas(
            @RequestBody UserSelectedAreasDTO dto,
            @RequestHeader (name="Authorization") String token){

        Long id = userAuthService.getIdFromToken(token);


        ServiceAnswer answer = editPersonalInformationService.UpdateUserAreas(id, dto);
        return createResponse(answer);
    }

    @PostMapping("/updateAreas/{userId}")
    public ResponseEntity<?> setSelectedAreas(
            @RequestBody UserSelectedAreasDTO dto,
            @PathVariable int userId){

        Long id = Long.valueOf( userId);
        ServiceAnswer answer = editPersonalInformationService.UpdateUserAreas(id, dto);
        return createResponse(answer);
    }

    @PostMapping("/editPersonalInformation")
    public ResponseEntity<?> editMiInformation(
            @RequestBody UserEditPersonalInformationDTO dto,
            @RequestHeader (name="Authorization") String token){

        Long id = userAuthService.getIdFromToken(token);

        ServiceAnswer answer =
                editPersonalInformationService.EditLimitedPersonalInformationById(id, dto);
        return createResponse(answer);
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
