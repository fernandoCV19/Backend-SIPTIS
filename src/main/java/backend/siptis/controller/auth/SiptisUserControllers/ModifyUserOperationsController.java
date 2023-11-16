package backend.siptis.controller.auth.SiptisUserControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterUserDTO;
import backend.siptis.model.pjo.dto.userDataDTO.UserEditInformationDTO;
import backend.siptis.model.pjo.dto.userDataDTO.UserSelectedAreasDTO;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceModifyUserOperations;
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
public class ModifyUserOperationsController {

    private final Set<ServiceMessage> okResponse = new HashSet<>(
            List.of(ServiceMessage.OK, ServiceMessage.SUCCESSFUL_REGISTER, ServiceMessage.USER_DELETED));
    private final SiptisUserServiceModifyUserOperations siptisUserServiceModifyUserOperations;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;

    @PostMapping("/register/general")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO dto) {
        ServiceAnswer user = siptisUserServiceModifyUserOperations.registerUser(dto);
        return createResponseEntity(user);
    }

    @PutMapping("/editInformation")
    public ResponseEntity<?> editInformation(
            @RequestHeader(name = "Authorization") String token,
            @Valid @RequestBody UserEditInformationDTO dto) {

        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answer = siptisUserServiceModifyUserOperations.userEditPersonalInformation(id, dto);
        return createResponseEntity(answer);
    }

    @PutMapping("/updateAreas")
    public ResponseEntity<?> updateAreas(
            @RequestHeader(name = "Authorization") String token, @RequestBody UserSelectedAreasDTO dto) {
        Long id = siptisUserServiceTokenOperations.getIdFromToken(token);
        ServiceAnswer answer = siptisUserServiceModifyUserOperations.updateAreas(id, dto);
        return createResponseEntity(answer);
    }

    @PutMapping("/updateAreas/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateAreas(@PathVariable int userId, @RequestBody UserSelectedAreasDTO dto) {
        Long id = Long.valueOf(userId);
        ServiceAnswer answer = siptisUserServiceModifyUserOperations.updateAreas(id, dto);
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
