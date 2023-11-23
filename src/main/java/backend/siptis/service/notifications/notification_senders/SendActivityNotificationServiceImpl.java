package backend.siptis.service.notifications.notification_senders;

import backend.siptis.service.notifications.EmailFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class SendActivityNotificationServiceImpl implements SendActivityNotificationService {
    private final TemplateEngine templateEngine;
    private final EmailFactory emailFactory;

    public void sendNotification(String titleActivity, String[] usersEmail, String activityDate) {
        emailFactory.setEmailArray(usersEmail);
        String subject = "Notificacion de las actividades proximas";
        emailFactory.setSubject(subject);
        emailFactory.setText(buildHtmlTemplate(titleActivity, activityDate));
        emailFactory.setIsHtml(Boolean.TRUE);
        emailFactory.massiveSend();
    }

    private String buildHtmlTemplate(String titleActivity, String activityDate) {
        Context context = new Context();
        context.setVariable("title", titleActivity);
        context.setVariable("date", activityDate);
        return templateEngine.process("notification", context);
    }
}
