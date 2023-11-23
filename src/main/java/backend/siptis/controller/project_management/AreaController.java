package backend.siptis.controller.project_management;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.general_information.user_area.CreateAreaDTO;
import backend.siptis.service.project_management.AreaService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Tag(name = ControllerConstants.Area.TAG_NAME, description = ControllerConstants.Area.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Area.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class AreaController {

    private final AreaService areaService;
    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.AREA_DELETED, ServiceMessage.AREA_CREATED));
    private final Set<ServiceMessage> notFoundResponse = new HashSet<>(
            List.of(ServiceMessage.AREA_NOT_FOUND));

    @Operation(summary = "Get all areas")
    @GetMapping("/getAreas")
    public ResponseEntity<?> getAllAreas() {
        ServiceAnswer answerService = areaService.getAllAreas();
        return createResponseEntity(answerService);
    }

    @Operation(summary = "Create a new area")
    @PostMapping("/createArea")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> createArea(@Valid @RequestBody CreateAreaDTO dto) {
        ServiceAnswer answerService = areaService.createArea(dto);
        return createResponseEntity(answerService);
    }

    @Operation(summary = "Delete an area by id")
    @DeleteMapping("/deleteArea/{areaId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteArea(@PathVariable int areaId) {
        Long id = Long.valueOf(areaId);
        ServiceAnswer answerService = areaService.deleteArea(id);
        return createResponseEntity(answerService);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if (okResponse.contains(messageService)) {
            httpStatus = HttpStatus.OK;
        } else if (notFoundResponse.contains(messageService)) {
            httpStatus = HttpStatus.NOT_FOUND;
        }

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
