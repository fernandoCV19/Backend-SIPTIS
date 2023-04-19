package backend.siptis.auth.recoverPassword.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("maury.vargasl@gmail.com");
        message.setTo("201900188@est.umss.edu");
        message.setSubject("Contenido del mail");

        javaMailSender.send(message);
    }
}
