package backend.siptis.controller.auth.SiptisUserControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceCareerDirectorOperations;
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

@Tag(name = ControllerConstants.SiptisUser.TAG_NAME, description = ControllerConstants.SiptisUser.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.SiptisUser.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class CareerDirectorOperationsController {
    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    private final SiptisUserServiceCareerDirectorOperations siptisUserServiceCareerDirectorOperations;

    @Operation(summary = "Get information of user with career director role")
    @GetMapping("/directorInformation/{career}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ControllerAnswer> getDirectorInfo(@PathVariable String career) {
        String directorRole = "SIS_DIRECTOR";
        if (career.equals("informatica")) {
            directorRole = "INF_DIRECTOR";
        }
        ServiceAnswer answer = siptisUserServiceCareerDirectorOperations.getDirectorPersonalInformation(directorRole);
        return createResponseEntity(answer);
    }

    @Operation(summary = "Register user as career director")
    @PostMapping("/register/director/{career}/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ControllerAnswer> registerCareerDirector(@PathVariable String career, @PathVariable int userId) {
        Long id = Long.valueOf(userId);
        String directorRole = "SIS_DIRECTOR";
        if (career.equals("informatica")) {
            directorRole = "INF_DIRECTOR";
        }
        ServiceAnswer answer = siptisUserServiceCareerDirectorOperations.registerUserAsCareerDirector(id, directorRole);
        return createResponseEntity(answer);
    }

    private ResponseEntity<ControllerAnswer> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if (okResponse.contains(messageService))
            httpStatus = HttpStatus.OK;

        if (messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.BAD_REQUEST;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
