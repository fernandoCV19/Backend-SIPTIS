package backend.siptis.service.notifications;

public interface WppService {
    void sendPersonalActivitiesWpp();
    void sendGeneralActivitiesWpp();
    void sendPersonalMessagesWpp(String to, String message);

}
