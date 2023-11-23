package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.generalInformation.userArea.CreateAreaDTO;
import backend.siptis.service.userData.UserAreaService;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Tag(name = ControllerConstants.UserArea.TAG_NAME, description = ControllerConstants.UserArea.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.UserArea.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class UserAreaController {

    private final UserAreaService userAreaService;
    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.AREA_DELETED, ServiceMessage.AREA_CREATED));
    private final Set<ServiceMessage> notFoundResponse = new HashSet<>(
            List.of(ServiceMessage.AREA_NOT_FOUND));

    @Operation(summary = "Get all user areas")
    @GetMapping("/getAreas")
    public ResponseEntity<ControllerAnswer> getAllAreas() {
        ServiceAnswer answerService = userAreaService.getAllUserAreas();
        return createResponseEntity(answerService);
    }
    @Operation(summary = "create a new user area")
    @PostMapping("/createArea")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ControllerAnswer> createArea(@RequestBody CreateAreaDTO dto) {
        ServiceAnswer answerService = userAreaService.createUserArea(dto);
        return createResponseEntity(answerService);
    }
    @Operation(summary = "delete user area")
    @DeleteMapping("/deleteArea/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ControllerAnswer> deleteArea(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = userAreaService.deleteUserArea(id);
        return createResponseEntity(answerService);
    }

    private ResponseEntity<ControllerAnswer> createResponseEntity(ServiceAnswer serviceAnswer) {
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
