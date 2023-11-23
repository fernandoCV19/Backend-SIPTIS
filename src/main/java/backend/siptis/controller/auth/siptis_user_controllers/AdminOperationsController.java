package backend.siptis.controller.auth.siptis_user_controllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.AdminEditUserInformationDTO;
import backend.siptis.model.pjo.dto.user_data.RegisterAdminDTO;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceAdminOperations;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
public class AdminOperationsController {

    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    private final SiptisUserServiceAdminOperations siptisUserServiceAdminOperations;

    @Operation(summary = "Register a new user with ADMIN role")
    @PostMapping("/register/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ControllerAnswer> registerAdmin(@Valid @RequestBody RegisterAdminDTO dto) {
        ServiceAnswer admin = siptisUserServiceAdminOperations.registerAdmin(dto);
        return createResponseEntity(admin);
    }

    @Operation(summary = "Get list of users with ADMIN role")
    @GetMapping("/list/admins")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    public ResponseEntity<ControllerAnswer> getAdminList(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserServiceAdminOperations.getAdminUserList(search, pageable);
        return createResponseEntity(answerService);
    }

    @Operation(summary = "Edit information of other user")
    @PutMapping("/editUserInformation/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ControllerAnswer> editUser(
            @PathVariable int userId,
            @Valid @RequestBody AdminEditUserInformationDTO dto) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserServiceAdminOperations.adminEditUserInformation(id, dto);
        return createResponseEntity(answer);
    }

    @Operation(summary = "Get list of users with TRIBUNAL role")
    @GetMapping("/getTribunals")
    ResponseEntity<ControllerAnswer> getPossibleTribunals() {
        ServiceAnswer answer = siptisUserServiceAdminOperations.getPossibleTribunals();
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
