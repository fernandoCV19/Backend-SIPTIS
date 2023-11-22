package backend.siptis.service.notifications.recoverPassword;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.userDataDTO.ChangePasswordDTO;
import backend.siptis.model.pjo.dto.userDataDTO.TokenPasswordDTO;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface RecoverPasswordEmailService {
    // TODO: esta clase busca toda la informacion para hacer el recover

    ServiceAnswer changePassword(TokenPasswordDTO tokenPasswordDTO);

    String readFile(String filen) throws IOException;

    ServiceAnswer sendRecoverPasswordEmail(String email) throws MessagingException, IOException;

    ChangePasswordDTO createChangePasswordDTO(String email);

}
