package backend.siptis.service.notifications.notification_senders;

import java.io.File;

public interface DocumentSenderService {
    void sendDocument(String message, String email, File file);

}
