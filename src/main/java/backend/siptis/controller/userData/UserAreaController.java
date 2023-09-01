package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;
import backend.siptis.service.userData.UserAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userArea")
@RequiredArgsConstructor
@CrossOrigin
public class UserAreaController {

    private final UserAreaService userAreaService;


    @GetMapping("/getAreas")
    public ResponseEntity<?> getAllAreas() {
        ServiceAnswer answerService = userAreaService.getAllUserAreas();
        return createResponseEntity(answerService);
    }


    @PostMapping("/createArea")
    public ResponseEntity<?> createArea(
            @RequestBody CreateAreaDTO dto) {
        ServiceAnswer answerService = userAreaService.createUserArea(dto);
        return createResponseEntity(answerService);
    }

    @DeleteMapping("/deleteArea/{userId}")
    public ResponseEntity<?> deleteArea(
            @PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = userAreaService.deleteUserArea(id);
        return createResponseEntity(answerService);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if(messageService == ServiceMessage.OK || messageService == ServiceMessage.USER_AREA_DELETED){
            httpStatus = HttpStatus.OK;
        }

        if(messageService == ServiceMessage.NOT_FOUND )
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}