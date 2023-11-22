package backend.siptis.service.notifications.recoverPassword;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.ChangePasswordDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.service.notifications.EmailFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SendRecoverPasswordEmailServiceImpl implements SendRecoverPasswordEmailService {
    private final TemplateEngine templateEngine;
    private final EmailFactory emailFactory;
    private final SiptisUserRepository siptisUserRepository;

    @Override
    public ServiceAnswer sendRecoverPasswordEmail(String email) {
        // TODO Este metodo arma el html y lo envia con el email factory
        Optional<SiptisUser> oUser =  siptisUserRepository.findOneByEmail(email);
        if (oUser.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.EMAIL_NOT_EXIST)
                    .data(null).build();
        }
        ChangePasswordDTO dto = createChangePasswordDTO(email);
        emailFactory.setToMail(email);
        String subject = "Notificacion de las actividades proximas";
        emailFactory.setSubject(subject);
        emailFactory.setText(buildHtmlTemplate(dto.getTokenPassword()));
        emailFactory.setIsHtml(Boolean.TRUE);
        emailFactory.send();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data("Se ha enviado un correo a su cuenta.").build();
    }

    private String buildHtmlTemplate(String token) {
        Context context = new Context();
        context.setVariable("url", token);
        return templateEngine.process("recoverpassword", context);
    }

    private ChangePasswordDTO createChangePasswordDTO(String email) {
        ChangePasswordDTO dto = new ChangePasswordDTO();
        dto.setEmailFrom("siptis.umss@gmail.com");
        dto.setEmailTo(email);
        dto.setSubject("Respuesta solicitud recuperacion de contraseña SIPTIS");
        UUID uuid = UUID.randomUUID();
        dto.setTokenPassword(uuid.toString());

        return dto;
    }
}
