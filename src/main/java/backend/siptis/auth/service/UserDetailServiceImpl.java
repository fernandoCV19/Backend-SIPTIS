package backend.siptis.auth.service;

import backend.siptis.auth.entity.User;
import backend.siptis.auth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usuarioRepository.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "El usuario con el nombre "+email+" no existe"
                ));

        return new UserDetailImp(user);
    }
}
