package backend.siptis.controller.notifications;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.user_data.TokenPasswordDTO;
import backend.siptis.service.notifications.recover_password.RecoverPasswordEmailService;
import backend.siptis.service.notifications.recover_password.SendRecoverPasswordEmailService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = ControllerConstants.Email.TAG_NAME, description = ControllerConstants.Email.TAG_DESCRIPTION)
@RestController
@CrossOrigin
@RequestMapping(ControllerConstants.Email.BASE_PATH)
@RequiredArgsConstructor
public class EmailController {

    private final RecoverPasswordEmailService emailServiceImpl;
    private final SendRecoverPasswordEmailService sendRecoverPasswordEmailService;

    @Operation(summary = "Ask email for send recover password link")
    @GetMapping("/askemail/{email}")
    public ResponseEntity<ControllerAnswer> sendEmailTest(@PathVariable String email)  {
        ServiceAnswer answer = sendRecoverPasswordEmailService.sendRecoverPasswordEmail(email);
        return crearResponseEntityRegistrar(answer);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ControllerAnswer> changePassword(@RequestBody TokenPasswordDTO dto) {
        ServiceAnswer answer = emailServiceImpl.changePassword(dto);
        return crearResponseEntityRegistrar(answer);
    }

    private ResponseEntity<ControllerAnswer> crearResponseEntityRegistrar(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if (messageService == ServiceMessage.OK) {
            httpStatus = HttpStatus.OK;
        }

        if (messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

}
