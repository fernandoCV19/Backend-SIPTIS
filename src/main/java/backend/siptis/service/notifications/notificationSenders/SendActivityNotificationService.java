package backend.siptis.service.notifications.notificationSenders;

public interface SendActivityNotificationService {
    void sendNotification(String titleActivity, String[] usersEmail, String activityDate);
}
