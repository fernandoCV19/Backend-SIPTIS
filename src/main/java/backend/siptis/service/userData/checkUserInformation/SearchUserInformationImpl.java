package backend.siptis.service.userData.checkUserInformation;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchUserInformationImpl implements SearchUserInformation{

    private final SiptisUserRepository usuarioCommonRepository;

    @Override
    public ServiceAnswer findAllUsers() {
        return null;
    }

    @Override
    public ServiceAnswer findAllStudents() {
        return null;
    }

    @Override
    public ServiceAnswer findAllTeachers() {
        return null;
    }

    @Override
    public ServiceAnswer findAllAdmins() {
        return null;
    }

    @Override
    public SiptisUser findByEmail(String email) {
        Optional<SiptisUser> user = usuarioCommonRepository.findByEmail(email);
        return user.get();
    }



    @Override
    public SiptisUser findByTokenPassword(String tokenPassword) {
        Optional<SiptisUser> optional = usuarioCommonRepository.findByTokenPassword(tokenPassword);
        return optional.get();
    }
}
