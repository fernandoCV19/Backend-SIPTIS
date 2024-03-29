package backend.siptis.service.user_data;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.user_data.UserInformation;
import backend.siptis.model.pjo.dto.user_data.AdminEditUserInformationDTO;
import backend.siptis.model.pjo.dto.user_data.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.user_data.RegisterUserDTO;
import backend.siptis.model.pjo.dto.user_data.UserEditInformationDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserInformationService {


    ServiceAnswer findById(Long id);

    ServiceAnswer registerUserInformation(RegisterStudentDTO dto);

    ServiceAnswer registerUserInformation(RegisterUserDTO dto);

    ServiceAnswer userEditInformation(UserInformation userInformation, UserEditInformationDTO dto);

    ServiceAnswer adminEditUserInformation(UserInformation userInformation, AdminEditUserInformationDTO dto);

    ServiceAnswer adminEditStudentInformation(UserInformation userInformation, AdminEditUserInformationDTO dto);


    @AllArgsConstructor
    class UserDetailImp implements UserDetails {
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

        public List<Long> getProjects() {
            if (siptisUser.getProjects() != null)
                return siptisUser.getProjects();
            return new ArrayList<>();
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

        public String getRoles() {
            Set<Role> roles = siptisUser.getRoles();
            List<String> rolesResponse = new ArrayList<>();
            for (Role rol : roles) {
                rolesResponse.add(rol.getName());
            }
            return siptisUser.getRoles().toString();
        }


    }
}
