package backend.siptis.service.notifications;

import jakarta.mail.internet.InternetAddress;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Scope(value = "prototype")
@Component
@RequiredArgsConstructor
public class EmailFactory {
    @Setter
    private String toMail;

    @Setter
    private String subject;

    @Setter
    private String text;

    @Setter
    private String[] emailArray;

    @Setter
    private Boolean isHtml;

    @Getter
    private String response;

    @Value("${spring.mail.host}")
    private String fromMail;

    @Value("${application.name}")
    private String from;

    private final JavaMailSender mailSender;

    public void send() {
        MimeMessagePreparator message = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(new InternetAddress(fromMail, from));
            messageHelper.setTo(toMail);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, isHtml);
        };

        try {
            mailSender.send(message);
            response = "EMAIL SENT SUCCESSFULLY";
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    public void massiveSend() {
        MimeMessagePreparator message = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(new InternetAddress(fromMail, from));
            messageHelper.setTo(emailArray);
            messageHelper.setSubject(subject);
            messageHelper.setText(text, isHtml);
        };

        try {
            mailSender.send(message);
            response = "EMAIL SENT SUCCESSFULLY";
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
