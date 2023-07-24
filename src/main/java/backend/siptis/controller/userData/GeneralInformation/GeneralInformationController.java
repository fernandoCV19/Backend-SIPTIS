package backend.siptis.controller.userData.GeneralInformation;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.UserSelectedAreasDTO;
import backend.siptis.service.generalInformation.AreasService;
import backend.siptis.service.generalInformation.UserAreaService;
import backend.siptis.service.generalInformation.UserCareerService;
import backend.siptis.service.userData.GeneralInformation.GeneralInformationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/siptis")
@AllArgsConstructor
@CrossOrigin
public class GeneralInformationController {

    @Autowired
    private final GeneralInformationService generalInformationService;

    private final UserCareerService userCareerService;
    private final UserAreaService userAreaService;
    private final AreasService areasService;

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
        ServiceAnswer answerService = areasService.getAllAreas();
        return createResponse(answerService);
    }

    @GetMapping("/getAllPotentialTribunals")
    public ResponseEntity<?> getAllPotentialTribunals(){
        ServiceAnswer answerService = generalInformationService.getAllPotentialTribunals();
        return createResponse(answerService);
    }

    @PostMapping("/getPotentialTribunalsByAreas")
    public ResponseEntity<?> getPotentialTribunalsByAreas(
            @RequestBody UserSelectedAreasDTO areas
    ){
        ServiceAnswer answerService = generalInformationService.getPotentialTribunalsByAreas(areas);
        return createResponse(answerService);
    }

    @PostMapping("/getProjectsByAreas")
    public ResponseEntity<?> getProjectsByAreas(
            @RequestBody UserSelectedAreasDTO areas
    ){
        ServiceAnswer answerService = generalInformationService.getProjectsByAreas(areas);
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
