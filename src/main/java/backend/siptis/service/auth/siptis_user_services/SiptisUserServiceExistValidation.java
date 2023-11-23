package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.model.repository.auth.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SiptisUserServiceExistValidation {

    private final SiptisUserRepository siptisUserRepository;

    public boolean existsUserByEmail(String email) {
        return siptisUserRepository.existsByEmail(email);
    }

    public boolean existCareerDirector(String directorRole) {
        return siptisUserRepository.existsByRolesName(directorRole);
    }

    public boolean existsUserById(long id) {
        return siptisUserRepository.existsById(id);
    }
}

