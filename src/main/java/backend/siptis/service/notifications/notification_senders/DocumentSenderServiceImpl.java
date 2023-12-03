package backend.siptis.service.notifications.notification_senders;

import backend.siptis.service.notifications.EmailFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

@Service
@RequiredArgsConstructor
public class DocumentSenderServiceImpl implements DocumentSenderService {

    private final TemplateEngine templateEngine;
    private final EmailFactory emailFactory;

    @Override
    public void sendDocument(String message, String email, File file) {
        emailFactory.setToMail(email);
        emailFactory.setSubject("Documento generado");
        emailFactory.setText(buildHtmlTemplate(message));
        emailFactory.setIsHtml(Boolean.TRUE);
        emailFactory.setFilename(file.getName());
        emailFactory.setFile(file);
        emailFactory.sendDocument();
    }

    private String buildHtmlTemplate(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("common_notification", context);
    }
}
