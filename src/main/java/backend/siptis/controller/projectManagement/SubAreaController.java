package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;
import backend.siptis.service.projectManagement.AreaService;
import backend.siptis.service.projectManagement.SubAreaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subarea")
@RequiredArgsConstructor
@CrossOrigin
public class SubAreaController {

    private final SubAreaService subAreaService;

    @GetMapping("/getSubAreas")
    public ResponseEntity<?> getAllSubAreas() {
        ServiceAnswer answerService = subAreaService.getAllSubAreas();
        return createResponseEntity(answerService);
    }

    @PostMapping("/createSubArea")
    public ResponseEntity<?> createSubArea(
            @Valid @RequestBody CreateAreaDTO dto) {
        ServiceAnswer answerService = subAreaService.createSubArea(dto);
        return createResponseEntity(answerService);
    }

    @DeleteMapping("/deleteSubArea/{userId}")
    public ResponseEntity<?> deleteArea(
            @PathVariable int userId) {
        Long id = Long.valueOf(userId);
        System.out.println(id);
        ServiceAnswer answerService = subAreaService.deleteSubArea(id);
        return createResponseEntity(answerService);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if(messageService == ServiceMessage.OK || messageService == ServiceMessage.AREA_DELETED){
            httpStatus = HttpStatus.OK;
        }

        if(messageService == ServiceMessage.NOT_FOUND )
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
