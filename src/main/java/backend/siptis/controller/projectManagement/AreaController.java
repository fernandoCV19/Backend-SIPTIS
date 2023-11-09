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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/area")
@RequiredArgsConstructor
@CrossOrigin
public class AreaController {

    private final AreaService areaService;
    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.AREA_DELETED, ServiceMessage.AREA_CREATED));
    private final Set<ServiceMessage> notFoundResponse = new HashSet<>(
            List.of(ServiceMessage.AREA_NOT_FOUND));

    @GetMapping("/getAreas")
    public ResponseEntity<?> getAllAreas() {
        ServiceAnswer answerService = areaService.getAllAreas();
        return createResponseEntity(answerService);
    }

    @PostMapping("/createArea")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createArea(@Valid @RequestBody CreateAreaDTO dto) {
        ServiceAnswer answerService = areaService.createArea(dto);
        return createResponseEntity(answerService);
    }

    @DeleteMapping("/deleteArea/{areaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteArea(@PathVariable int areaId) {
        Long id = Long.valueOf(areaId);
        ServiceAnswer answerService = areaService.deleteArea(id);
        return createResponseEntity(answerService);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if(okResponse.contains(messageService)){
            httpStatus = HttpStatus.OK;
        }else if (notFoundResponse.contains(messageService)){
            httpStatus = HttpStatus.NOT_FOUND;
        }

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
