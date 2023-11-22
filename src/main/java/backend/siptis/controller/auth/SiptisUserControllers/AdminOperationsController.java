package backend.siptis.controller.auth.SiptisUserControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.AdminEditUserInformationDTO;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterAdminDTO;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceAdminOperations;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
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

@RestController
@RequestMapping(ControllerConstants.SiptisUser.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class AdminOperationsController {

    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    private final SiptisUserServiceAdminOperations siptisUserServiceAdminOperations;

    @PostMapping("/register/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody RegisterAdminDTO dto) {
        ServiceAnswer admin = siptisUserServiceAdminOperations.registerAdmin(dto);
        return createResponseEntity(admin);
    }

    @GetMapping("/list/admins")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STUDENT')")
    public ResponseEntity<?> getAdminList(String search, Pageable pageable) {
        ServiceAnswer answerService =
                siptisUserServiceAdminOperations.getAdminUserList(search, pageable);
        return createResponseEntity(answerService);
    }

    @PutMapping("/editUserInformation/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> editUser(
            @PathVariable int userId,
            @Valid @RequestBody AdminEditUserInformationDTO dto) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserServiceAdminOperations.adminEditUserInformation(id, dto);
        return createResponseEntity(answer);
    }

    @GetMapping("/getTribunals")
    ResponseEntity<?> getPossibleTribunals() {
        ServiceAnswer answer = siptisUserServiceAdminOperations.getPossibleTribunals();
        return createResponseEntity(answer);
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
