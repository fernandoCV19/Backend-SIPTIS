package backend.siptis.controller.auth.siptis_user_controllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.RolesListDTO;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceRolesOperations;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceTokenOperations;
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

@Tag(name = ControllerConstants.SiptisUser.TAG_NAME, description = ControllerConstants.SiptisUser.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.SiptisUser.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class RolesOperationsController {

    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;
    private final SiptisUserServiceRolesOperations siptisUserServiceRolesOperations;

    @Operation(summary = "get own roles")
    @GetMapping("/roles")
    public ResponseEntity<ControllerAnswer> getRoles(@RequestHeader(name = "Authorization") String token) {
        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserServiceRolesOperations.getRolesById(id);
        return createResponseEntity(answerService);
    }

    @Operation(summary = "get roles from other user")
    @GetMapping("/roles/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> getRolesById(@PathVariable int userId) {
        Long id = (long) userId;
        ServiceAnswer answerService = siptisUserServiceRolesOperations.getRolesById(id);
        return createResponseEntity(answerService);
    }

    @Operation(summary = "update roles from other user")
    @PutMapping("/updateRoles/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> updateRoles(@PathVariable int userId, @Valid @RequestBody RolesListDTO dto) {
        Long id = (long) userId;
        ServiceAnswer answerService = siptisUserServiceRolesOperations.updateRoles(id, dto);
        return createResponseEntity(answerService);
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
