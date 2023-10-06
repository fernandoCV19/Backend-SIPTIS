package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;
import backend.siptis.service.projectManagement.AreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/area")
@RequiredArgsConstructor
@CrossOrigin
public class AreaController {

    private final AreaService areaService;

    @GetMapping("/getAreas")
    public ResponseEntity<?> getAllAreas() {
        ServiceAnswer answerService = areaService.getAllAreas();
        return createResponseEntity(answerService);
    }


    @PostMapping("/createArea")
    public ResponseEntity<?> createArea(
            @Valid @RequestBody CreateAreaDTO dto) {
        ServiceAnswer answerService = areaService.createArea(dto);
        return createResponseEntity(answerService);
    }

    @DeleteMapping("/deleteArea/{userId}")
    public ResponseEntity<?> deleteArea(
            @PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = areaService.deleteArea(id);
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
