package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.repository.userData.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInformationImpl implements UserInformationService{

    private final UserInformationRepository userInformationRepository;

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
        return userInformationRepository.existsById(id.intValue());
    }

    @Override
    public UserInformation findById(Long id) {

        int userId = id.intValue();
        Optional<UserInformation> optional = userInformationRepository.findById(userId);
        return optional.get();
    }
}
