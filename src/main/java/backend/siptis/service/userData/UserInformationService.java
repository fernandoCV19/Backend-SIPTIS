package backend.siptis.service.userData;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.userDataDTO.*;
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
    ServiceAnswer adminEditStudentInformation(UserInformation userInformation, AdminEditStudentInformationDTO dto);


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

        public ArrayList<Long> getProjects(){
            if(siptisUser.getProjects() != null)
                return siptisUser.getProjects();
            return new ArrayList<Long>();
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
            Set<Role> roles = siptisUser.getRoles();
            List<String> rolesResponse = new ArrayList<>();
            for (Role rol: roles) {
                rolesResponse.add(rol.getName());
            }
             return siptisUser.getRoles().toString();
        }


    }
}
