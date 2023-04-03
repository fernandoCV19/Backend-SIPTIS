package backend.siptis.service.records;

import backend.siptis.model.pjo.vo.GeneralActivityVO;
import jakarta.mail.Address;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class EmailService {
    private JavaMailSender mailSender;
    private final ActivityService activityService;
    private final GeneralActivityService generalActivityService;

    @Autowired
    public EmailService(JavaMailSender mailSender, ActivityService activityService, GeneralActivityService generalActivityService) {
        this.mailSender = mailSender;
        this.activityService = activityService;
        this.generalActivityService = generalActivityService;
    }

    public void send(String from, String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    //@Scheduled(cron = "0 0 8 * * *")
    @Scheduled(cron = "0 * * * * *")
    public void prueba(){
        System.out.println(LocalDateTime.now());
        int mesActual = LocalDateTime.now().getMonthValue();
        int diaActual = LocalDateTime.now().getDayOfMonth();

        List<GeneralActivityVO> generalActivityList = generalActivityService.findAllVO();
        for(GeneralActivityVO vo: generalActivityList){
            Date date =  vo.getFechaActividad();
            if(date.getMonth() == mesActual &&
                    (
                            diaActual == date.getDay() - 5  ||
                            diaActual == date.getDay() - 3 ||
                            diaActual == date.getDay() - 1)){


            }
        }
        System.out.println("crono");
    }
    public String readFile(String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        String content;

        File file = resource.getFile();
        content = new String(Files.readAllBytes(file.toPath()));

        return content;
    }

    public void sendEmailFromTemplate(Address[] arrayTO) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("siptis.umss@gmail.com"));

        message.setRecipients(MimeMessage.RecipientType.TO, arrayTO);
        message.setSubject("Test email for SIPTIS");

        String htmlTemplate = readFile("template.html");
        message.setContent(htmlTemplate, "text/html; charset=utf-8");

        mailSender.send(message);
    }
    public void sendEmailFromTemplate(String to) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("siptis.umss@gmail.com"));

        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("Test email for SIPTIS");

        String htmlTemplate = readFile("template.html");
        message.setContent(htmlTemplate, "text/html; charset=utf-8");

        mailSender.send(message);
    }
}
