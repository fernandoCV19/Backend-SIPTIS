package backend.siptis.controller.projectManagement;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;
import backend.siptis.service.projectManagement.SubAreaService;
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
@RequestMapping("/subarea")
@RequiredArgsConstructor
@CrossOrigin
public class SubAreaController {

    private final SubAreaService subAreaService;
    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUB_AREA_DELETED, ServiceMessage.SUB_AREA_CREATED));
    private final Set<ServiceMessage> notFoundResponse = new HashSet<>(
            List.of(ServiceMessage.SUB_AREA_NOT_FOUND));

    @GetMapping("/getSubAreas")
    public ResponseEntity<?> getAllSubAreas() {
        ServiceAnswer answerService = subAreaService.getAllSubAreas();
        return createResponseEntity(answerService);
    }

    @PostMapping("/createSubArea")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createSubArea(
            @Valid @RequestBody CreateAreaDTO dto) {
        ServiceAnswer answerService = subAreaService.createSubArea(dto);
        return createResponseEntity(answerService);
    }

    @DeleteMapping("/deleteSubArea/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteArea(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = subAreaService.deleteSubArea(id);
        return createResponseEntity(answerService);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if (okResponse.contains(messageService)) {
            httpStatus = HttpStatus.OK;
        } else if (notFoundResponse.contains(messageService))
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
