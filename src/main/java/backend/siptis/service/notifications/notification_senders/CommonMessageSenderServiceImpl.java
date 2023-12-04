package backend.siptis.service.notifications.notification_senders;

import backend.siptis.service.notifications.EmailFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class CommonMessageSenderServiceImpl implements CommonMessageSenderService {
    private final TemplateEngine templateEngine;
    private final EmailFactory emailFactory;

    @Override
    public void sendCommonNotificaction(String email, String message) {
        emailFactory.setToMail(email);
        emailFactory.setSubject("Documento generado");
        emailFactory.setText(buildHtmlTemplate(message));
        emailFactory.setIsHtml(Boolean.TRUE);
        emailFactory.send();
    }

    private String buildHtmlTemplate(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("common_notification", context);
    }
}
