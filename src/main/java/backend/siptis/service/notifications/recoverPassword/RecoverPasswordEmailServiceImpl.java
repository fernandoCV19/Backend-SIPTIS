package backend.siptis.service.notifications.recoverPassword;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.userDataDTO.TokenPasswordDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class RecoverPasswordEmailServiceImpl implements RecoverPasswordEmailService {
    private final SiptisUserRepository siptisUserRepository;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;


    @Override
    public ServiceAnswer changePassword(TokenPasswordDTO dto) {
        boolean check = (boolean) siptisUserServiceTokenOperations.existsTokenPassword(dto.getTokenPassword()).getData();
        if (!check) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.INVALID_TOKEN)
                    .data("INVALID TOKEN").build();
        }
        SiptisUser user = siptisUserServiceTokenOperations.findByTokenPassword(dto.getTokenPassword());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(dto.getPassword());
        user.setPassword(password);
        user.setTokenPassword(null);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data("PASSWORD UPDATED").build();
    }
}
