package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SiptisUserRepository siptisUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SiptisUser siptisUser = siptisUserRepository.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "NOT_FOUND"
                ));

        return new UserInformationService.UserDetailImp(siptisUser);
    }
}
