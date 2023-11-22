package backend.siptis.controller.auth.SiptisUserControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.RolesListDTO;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceRolesOperations;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
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
@RequestMapping(ControllerConstants.SiptisUser.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class RolesOperationsController {

    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;
    private final SiptisUserServiceRolesOperations siptisUserServiceRolesOperations;

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles(@RequestHeader(name = "Authorization") String token) {
        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserServiceRolesOperations.getRolesById(id);
        return createResponseEntity(answerService);
    }

    @GetMapping("/roles/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<?> getRolesById(@PathVariable int userId) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserServiceRolesOperations.getRolesById(id);
        return createResponseEntity(answerService);
    }

    @PutMapping("/updateRoles")
    public ResponseEntity<?> updateRoles(@RequestHeader(name = "Authorization") String token, @Valid @RequestBody RolesListDTO dto) {
        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answerService = siptisUserServiceRolesOperations.updateRoles(id, dto);
        return createResponseEntity(answerService);
    }

    @PutMapping("/updateRoles/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<?> updateRoles(@PathVariable int userId, @Valid @RequestBody RolesListDTO dto) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answerService = siptisUserServiceRolesOperations.updateRoles(id, dto);
        return createResponseEntity(answerService);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer) {
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
