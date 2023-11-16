package backend.siptis.service.notifications;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.ChangePasswordDTO;
import backend.siptis.model.pjo.dto.TokenPasswordDTO;
import backend.siptis.model.pjo.vo.GeneralActivityVO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceGeneralUserOperations;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import backend.siptis.service.notifications.generalActivityServices.GeneralActivityServiceFindOperations;
import jakarta.mail.Address;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final GeneralActivityServiceFindOperations generalActivityServiceFindOperations;
    private final SiptisUserRepository siptisUserRepository;
    private final SiptisUserServiceGeneralUserOperations siptisUserServiceGeneralUserOperations;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;
    private JavaMailSender mailSender;

    @Override
    @Scheduled(cron = "0 0 8 * * *")
    public void sendGeneralActivities() throws MessagingException, IOException {
        int mesActual = LocalDateTime.now().getMonthValue();
        int diaActual = LocalDateTime.now().getDayOfMonth();

        List<GeneralActivityVO> generalActivityList = generalActivityServiceFindOperations.findAllVO();
        List<SiptisUser> users = (List) siptisUserServiceGeneralUserOperations.getAllUsers().getData();
        Address[] addresses = getAllEmails(users);
        for (GeneralActivityVO vo : generalActivityList) {

            Date date = vo.getActivityDate();
            int activityDay = date.getDate();
            int activityMonth = date.getMonth() + 1;
            if (activityMonth == mesActual &&
                    (
                            diaActual == activityDay - 1 ||
                                    diaActual >= activityDay - 3 ||
                                    diaActual >= activityDay - 5
                    )) {

                sendEmailFromTemplate(addresses, vo.getActivityName(), vo.getActivityDescription(), vo.getActivityDate());

            }
        }
    }

    @Override
    public ServiceAnswer changePassword(TokenPasswordDTO dto) {
        boolean check = (boolean) siptisUserServiceTokenOperations.existsTokenPassword(dto.getTokenPassword()).getData();
        if (!check) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.INVALID_TOKEN)
                    .data("INVALID TOKEN").build();
        }
        SiptisUser user = siptisUserServiceTokenOperations.findByTokenPassword(dto.getTokenPassword());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(dto.getPassword());
        user.setPassword(password);
        user.setTokenPassword(null);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data("PASSWORD UPDATED").build();
    }


    private Address[] getAllEmails(List<SiptisUser> users) throws MessagingException {
        Address[] addresses = new Address[users.size()];
        for (int i = 0; i < users.size(); i++) {
            String email = users.get(i).getEmail();
            addresses[i] = new InternetAddress(email);
        }
        return addresses;
    }

    @Override
    public String readFile(String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        String content;

        File file = resource.getFile();
        content = new String(Files.readAllBytes(file.toPath()));

        return content;
    }

    @Override
    public void sendEmailFromTemplate(Address[] arrayTO, String activityName,
                                      String activityDescription, Date activityDate) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("siptis.umss@gmail.com"));

        message.setRecipients(MimeMessage.RecipientType.TO, arrayTO);
        message.setSubject("Notificacion de SIPTIS");

        String htmlTemplate = readFile("record.html");

        htmlTemplate = htmlTemplate.replace("#nombre", activityName);
        htmlTemplate = htmlTemplate.replace("#date", activityDate.toString());

        message.setContent(htmlTemplate, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    @Override
    public void sendSpecificEmail(String email, String messageNotification) throws MessagingException, IOException {
        MimeMessage message = mailSender.createMimeMessage();

        message.setFrom(new InternetAddress("siptis.umss@gmail.com"));

        message.setRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("Notificacion de SIPTIS");

        String htmlTemplate = readFile("notification.html");


        htmlTemplate = htmlTemplate.replace("#message", messageNotification);

        message.setContent(htmlTemplate, "text/html; charset=utf-8");

        mailSender.send(message);
    }

    @Override
    public ServiceAnswer sendRecoverPasswordEmail(String email) throws MessagingException {

        boolean checkUser = siptisUserRepository.existsByEmail(email);
        if (!checkUser) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.EMAIL_NOT_EXIST)
                    .data(null).build();
        }
        MimeMessage message = mailSender.createMimeMessage();
        SiptisUser user = siptisUserRepository.findOneByEmail(email).get();

        ChangePasswordDTO dto = createChangePasswordDTO(email);

        try {
            message.setFrom(new InternetAddress(dto.getEmailFrom()));
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject(dto.getSubject());
            user.setTokenPassword(dto.getTokenPassword());

            String htmlTemplate = readFile("recoverpassword.html");
            htmlTemplate = htmlTemplate.replace("#url", dto.getTokenPassword());
            message.setContent(htmlTemplate, "text/html; charset=utf-8");
            mailSender.send(message);

        } catch (IOException io) {
            System.out.println("Error al enviar mensaje");
        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data("Se ha enviado un correo a su cuenta.").build();

    }

    @Override
    public ChangePasswordDTO createChangePasswordDTO(String email) {
        ChangePasswordDTO dto = new ChangePasswordDTO();
        dto.setEmailFrom("siptis.umss@gmail.com");
        dto.setEmailTo(email);
        dto.setSubject("Respuesta solicitud recuperacion de contraseña SIPTIS");
        UUID uuid = UUID.randomUUID();
        dto.setTokenPassword(uuid.toString());

        return dto;
    }
}
