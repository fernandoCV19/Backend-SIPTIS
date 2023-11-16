package backend.siptis.controller.notifications;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.TokenPasswordDTO;
import backend.siptis.service.notifications.EmailServiceImpl;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping(ControllerConstants.Email.BASE_PATH)
@RequiredArgsConstructor
public class EmailController {

    private final EmailServiceImpl emailServiceImpl;

    @GetMapping("")
    public String sendNotification() throws MessagingException, IOException {
        emailServiceImpl.sendSpecificEmail("dilanantezana@gmail.com", "hola como estas");
        return "ok";
    }

    @GetMapping("/askemail/{email}")
    public ResponseEntity<?> sendEmailTest(@PathVariable String email) throws MessagingException {
        ServiceAnswer answer = emailServiceImpl.sendRecoverPasswordEmail(email);

        return crearResponseEntityRegistrar(answer);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody TokenPasswordDTO dto) {
        ServiceAnswer answer = emailServiceImpl.changePassword(dto);
        return crearResponseEntityRegistrar(answer);
    }

    private ResponseEntity<?> crearResponseEntityRegistrar(ServiceAnswer serviceAnswer) {
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
