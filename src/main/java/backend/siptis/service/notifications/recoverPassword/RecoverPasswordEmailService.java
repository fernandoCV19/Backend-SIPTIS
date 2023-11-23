package backend.siptis.service.notifications.recoverPassword;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.userDataDTO.TokenPasswordDTO;

public interface RecoverPasswordEmailService {
    ServiceAnswer changePassword(TokenPasswordDTO tokenPasswordDTO);


}
