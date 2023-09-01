package backend.siptis.service.notifications;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.ChangePasswordDTO;
import backend.siptis.model.pjo.dto.TokenPasswordDTO;
import jakarta.mail.Address;
import jakarta.mail.MessagingException;

import java.io.IOException;
import java.util.Date;

public interface EmailService {
    void sendPersonalActivities() throws MessagingException, IOException;
    void sendGeneralActivities() throws MessagingException, IOException;
    ServiceAnswer changePassword(TokenPasswordDTO tokenPasswordDTO);
    String readFile(String filen) throws IOException;
    void sendEmailFromTemplate(Address[] arrayTO, String activityName, String activityDescription,
                               Date ActivityDate) throws MessagingException, IOException;
    void sendSpecificEmail(String email, String messageNotification) throws MessagingException, IOException;
    ServiceAnswer sendRecoverPasswordEmail(String email) throws MessagingException, IOException;
    ChangePasswordDTO createChangePasswordDTO(String email);
}
