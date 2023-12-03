package backend.siptis.service.notifications.notification_senders;

import jakarta.mail.MessagingException;

import java.io.File;
import java.io.IOException;

public interface DocumentSenderService {
    void sendDocument(String message, String email, File file);

}
