package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@AllArgsConstructor
public class UserDetailImp implements UserDetails {
    private SiptisUser siptisUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return siptisUser.getAuthorities();
    }

    @Override
    public String getPassword() {
        return siptisUser.getPassword();
    }


    public Long getId() {
        return siptisUser.getId();
    }

    @Override
    public String getUsername() {
        return siptisUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getRoles(){
        return siptisUser.getRoles().toString();
    }


}
