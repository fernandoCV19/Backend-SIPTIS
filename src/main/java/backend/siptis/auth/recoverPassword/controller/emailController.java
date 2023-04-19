package backend.siptis.auth.recoverPassword.controller;

import backend.siptis.auth.recoverPassword.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class emailController {

    @Autowired
    EmailService service;

    @GetMapping("/email/send")
    public ResponseEntity<?> sendEmail(){
        service.sendEmail();
        return  new ResponseEntity("Correo enviado con exito", HttpStatus.OK);
    }
}
