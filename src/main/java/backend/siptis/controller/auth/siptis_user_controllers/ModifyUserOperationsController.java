package backend.siptis.controller.auth.siptis_user_controllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.RegisterUserDTO;
import backend.siptis.model.pjo.dto.user_data.UserEditInformationDTO;
import backend.siptis.model.pjo.dto.user_data.UserSelectedAreasDTO;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceModifyUserOperations;
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
public class ModifyUserOperationsController {

    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    private final SiptisUserServiceModifyUserOperations siptisUserServiceModifyUserOperations;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;

    @Operation(summary = "Register general user")
    @PostMapping("/register/general")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> registerUser(@Valid @RequestBody RegisterUserDTO dto) {
        ServiceAnswer user = siptisUserServiceModifyUserOperations.registerUser(dto);
        return createResponseEntity(user);
    }

    @Operation(summary = "Edit own information")
    @PutMapping("/editInformation")
    public ResponseEntity<ControllerAnswer> editInformation(
            @RequestHeader(name = "Authorization") String token,
            @Valid @RequestBody UserEditInformationDTO dto) {

        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answer = siptisUserServiceModifyUserOperations.userEditPersonalInformation(id, dto);
        return createResponseEntity(answer);
    }

    @Operation(summary = "Edit own user areas")
    @PutMapping("/updateAreas")
    public ResponseEntity<ControllerAnswer> updateAreas(
            @RequestHeader(name = "Authorization") String token, @RequestBody UserSelectedAreasDTO dto) {
        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answer = siptisUserServiceModifyUserOperations.updateAreas(id, dto);
        return createResponseEntity(answer);
    }

    @Operation(summary = "Edit user areas of other user")
    @PutMapping("/updateAreas/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    public ResponseEntity<ControllerAnswer> updateAreas(@PathVariable int userId, @RequestBody UserSelectedAreasDTO dto) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserServiceModifyUserOperations.updateAreas(id, dto);
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
