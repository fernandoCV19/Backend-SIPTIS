package backend.siptis.controller.notifications;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.TokenPasswordDTO;
import backend.siptis.service.notifications.EmailServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/email")
public class EmailController {
    private EmailServiceImpl emailServiceImpl;
    @Autowired
    public EmailController(EmailServiceImpl emailServiceImpl){
        this.emailServiceImpl = emailServiceImpl;
    }
    @GetMapping("")
    public String sendNotification() throws MessagingException, IOException {
        System.out.print("hola");
        //emailService.sendEmailFromTemplate("dilanantezana@gmail.com");

        return "ok";
    }

    @GetMapping("/askemail/{email}")
    public ResponseEntity<?> sendEmailTest(@PathVariable String email) throws MessagingException, IOException {

        ServiceAnswer answer = emailServiceImpl.sendRecoverPasswordEmail(email);

        //return "mensaje enviado con exito";
        return crearResponseEntityRegistrar(answer);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody TokenPasswordDTO dto){
        ServiceAnswer answer = emailServiceImpl.changePassword(dto);
        return crearResponseEntityRegistrar(answer);
        //return "hola";
    }

    private ResponseEntity<?> crearResponseEntityRegistrar(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if(messageService == ServiceMessage.OK){
            httpStatus = HttpStatus.OK;
        }


        if(messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
