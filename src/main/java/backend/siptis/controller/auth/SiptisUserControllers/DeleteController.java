package backend.siptis.controller.auth.SiptisUserControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceDelete;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(ControllerConstants.SiptisUser.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class DeleteController {

    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    private final SiptisUserServiceDelete siptisUserServiceDelete;

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ControllerAnswer> deleteUser(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserServiceDelete.deleteUser(id);
        return createResponseEntity(answer);
    }

    @DeleteMapping("/removeDirector/{career}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ControllerAnswer> removeDirector(@PathVariable String career) {
        String directorRole = "SIS_DIRECTOR";
        if (career.equals("informatica")) {
            directorRole = "INF_DIRECTOR";
        }
        ServiceAnswer answer = siptisUserServiceDelete.removeDirectorRole(directorRole);
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
