package backend.siptis.service.notifications.recover_password;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.user_data.TokenPasswordDTO;

public interface RecoverPasswordEmailService {
    ServiceAnswer changePassword(TokenPasswordDTO tokenPasswordDTO);


}
