package backend.siptis.controller.records;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.records.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/email")
public class EmailController {
    private EmailService emailService;
    @Autowired
    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }
    @GetMapping("")
    public String sendNotification() throws MessagingException, IOException {
        System.out.print("hola");
        //emailService.sendEmailFromTemplate("dilanantezana@gmail.com");

        return "ok";
    }

    @GetMapping("/prueba")
    public String sendEmailTest() throws MessagingException, IOException {
        emailService.sendRecoverPasswordEmail("maury.vargasl@gmail.com");

        return "mensaje enviado con exito";
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
