package backend.siptis.service.notifications;

public interface SendActivityNotificationService {
    void sendNotification(String titleActivity, String [] usersEmail, String activityDate);
}
