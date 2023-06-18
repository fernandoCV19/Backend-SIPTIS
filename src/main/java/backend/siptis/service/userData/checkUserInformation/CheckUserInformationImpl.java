package backend.siptis.service.userData.checkUserInformation;

import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.model.repository.userData.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckUserInformationImpl implements CheckUserInformation{

    private final UserInformationRepository userInformationRepository;
    private final SiptisUserRepository userRepository;

    @Override
    public boolean existByCodSIS(String codSIS) {
        return userInformationRepository.existsByCodSIS(codSIS);
    }

    @Override
    public boolean existByCi(String ci) {
        return userInformationRepository.existsByCi(ci);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsTokenPassword(String tokenPassword) {
        return userRepository.existsByTokenPassword(tokenPassword);
    }
}
