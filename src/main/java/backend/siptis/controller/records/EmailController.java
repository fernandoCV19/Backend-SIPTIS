package backend.siptis.controller.records;

import backend.siptis.service.records.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/email")
public class EmailController {
    private EmailService emailService;
    @Autowired
    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }
    @GetMapping("")
    public void sendEmail(){
        emailService.send("prueba", "dilanantezana@gmail.com", "algo", "esta es una prueba");
    }
}
