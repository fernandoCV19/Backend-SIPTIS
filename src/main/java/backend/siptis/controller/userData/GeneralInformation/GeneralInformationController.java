package backend.siptis.controller.userData.GeneralInformation;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.projectManagement.AreaService;
import backend.siptis.service.userData.UserAreaService;
import backend.siptis.service.userData.UserCareerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/siptis")
@AllArgsConstructor
@CrossOrigin
public class GeneralInformationController {



    private final UserCareerService userCareerService;
    private final UserAreaService userAreaService;
    private final AreaService areaService;

    @GetMapping("/getAllCareers")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<?> getAllCareers(){
        ServiceAnswer answerService = userCareerService.getAllCareers();
        return createResponse(answerService);
    }

    @GetMapping("/getAllAreas")
    public ResponseEntity<?> getAllUserAreas(){
        ServiceAnswer answerService = userAreaService.getAllUserAreas();
        return createResponse(answerService);
    }

    @GetMapping("/getAllProjectAreas")
    public ResponseEntity<?> getAllProjectAreas(){
        ServiceAnswer answerService = areaService.getAllAreas();
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
