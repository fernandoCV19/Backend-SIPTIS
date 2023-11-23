package backend.siptis.service.user_data;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final SiptisUserRepository siptisUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SiptisUser siptisUser = siptisUserRepository.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "NOT_FOUND"
                ));

        return new UserInformationService.UserDetailImp(siptisUser);
    }
}
