package backend.siptis.service.notifications.recoverPassword;

import backend.siptis.commons.ServiceAnswer;

public interface SendRecoverPasswordEmailService {
    ServiceAnswer sendRecoverPasswordEmail(String email);
}
