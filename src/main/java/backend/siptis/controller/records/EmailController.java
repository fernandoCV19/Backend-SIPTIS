package backend.siptis.controller.records;

import backend.siptis.service.records.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
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
}
