package backend.siptis.service.notifications.notification_senders;

public interface SendActivityNotificationService {
    void sendNotification(String titleActivity, String[] usersEmail, String activityDate);
}
