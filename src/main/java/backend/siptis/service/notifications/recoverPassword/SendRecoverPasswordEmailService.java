package backend.siptis.service.notifications.recoverPassword;

public interface SendRecoverPasswordEmailService {
    void sendRecoverPasswordEmail(String email, String token);
}
