package backend.siptis.service.notifications.recover_password;

import backend.siptis.commons.ServiceAnswer;

public interface SendRecoverPasswordEmailService {
    ServiceAnswer sendRecoverPasswordEmail(String email);
}
