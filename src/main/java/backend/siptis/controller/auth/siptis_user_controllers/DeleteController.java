package backend.siptis.controller.auth.siptis_user_controllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceDelete;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceTokenOperations;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Tag(name = ControllerConstants.SiptisUser.TAG_NAME, description = ControllerConstants.SiptisUser.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.SiptisUser.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class DeleteController {

    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    private final SiptisUserServiceDelete siptisUserServiceDelete;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;

    @Operation(summary = "Delete user")
    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ControllerAnswer> deleteUser(@RequestHeader(name = "Authorization") String token, @PathVariable int userId) {
        Long id = Long.valueOf(userId);
        Long adminId = siptisUserServiceTokenOperations.getIdFromToken(token);
        if (Objects.equals(id, adminId)) {
            ServiceAnswer answer = new ServiceAnswer();
            answer.setServiceMessage(ServiceMessage.ERROR_CANNOT_DELETE_USER);
            return createResponseEntity(answer);
        }
        ServiceAnswer answer = siptisUserServiceDelete.deleteUser(id);
        return createResponseEntity(answer);
    }

    @Operation(summary = "Remove career director role from user")
    @DeleteMapping("/removeDirector/{career}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<ControllerAnswer> removeDirector(@PathVariable String career) {
        String directorRole = "SIS_DIRECTOR";
        career = career.toUpperCase();
        if (career.equals("INFORMATICA")) {
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
