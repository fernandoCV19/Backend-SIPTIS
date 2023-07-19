package backend.siptis.service.userData;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.records.PersonalInformationDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.UserPersonalInformationDTO;
import jakarta.mail.search.SearchTerm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface UserInformationService {

    ServiceAnswer existByCodSIS(String codSIS);
    ServiceAnswer existByCi(String ci);
    ServiceAnswer existsById(Long id);

    ServiceAnswer findById(Long id);

    ServiceAnswer getAllStudents();
    ServiceAnswer getAllTeachers();
    ServiceAnswer getAllAdmin();

    ServiceAnswer searchUserByNameAndRole(String name, Long role_id);

    ServiceAnswer getUserPersonalInformation(SiptisUser user);

    ServiceAnswer getTeacherNotSelectedAreasById(Long id);

    ServiceAnswer registerUserInformation(UserPersonalInformationDTO dto);


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
            //return rolesResponse;
        }


    }
}
