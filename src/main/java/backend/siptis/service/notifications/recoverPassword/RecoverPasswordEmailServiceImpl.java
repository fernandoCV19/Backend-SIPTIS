package backend.siptis.service.notifications.recoverPassword;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.ChangePasswordDTO;
import backend.siptis.model.pjo.dto.userDataDTO.TokenPasswordDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceGeneralUserOperations;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import backend.siptis.service.notifications.generalActivityServices.GeneralActivityServiceFindOperations;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class RecoverPasswordEmailServiceImpl implements RecoverPasswordEmailService {
    private final SiptisUserRepository siptisUserRepository;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;
    // private JavaMailSender mailSender;


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

    @Override
    public String readFile(String fileName) throws IOException {
        Resource resource = new ClassPathResource(fileName);
        String content;

        File file = resource.getFile();
        content = new String(Files.readAllBytes(file.toPath()));

        return content;
    }

    @Override
    public ServiceAnswer sendRecoverPasswordEmail(String email) throws MessagingException {

/*
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
        }

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data("Se ha enviado un correo a su cuenta.").build();*/
        return null;

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
