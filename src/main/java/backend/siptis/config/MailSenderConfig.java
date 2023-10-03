package backend.siptis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailSenderConfig {

    @Bean("javaMailSender")
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        /*sender.setHost("smtp.mailtrap.io");
        sender.setPort(2525);
        sender.setUsername("b5af47ae4a8869");
        sender.setPassword("cdadfc08345cb6");
        /*/
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setUsername("siptis.umss@gmail.com");
        sender.setPassword("ftryberzfpkddfvj");

        Properties props = sender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return sender;
    }
}