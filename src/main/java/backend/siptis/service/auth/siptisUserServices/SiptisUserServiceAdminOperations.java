package backend.siptis.service.auth.siptisUserServices;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.Roles;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.userDataDTO.AdminEditUserInformationDTO;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterAdminDTO;
import backend.siptis.model.pjo.dto.userDataDTO.UserInformationDTO;
import backend.siptis.model.pjo.vo.userData.TribunalInfoToAssignSection;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.service.auth.RoleService;
import backend.siptis.service.userData.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class SiptisUserServiceAdminOperations {

    private final SiptisUserServiceGeneralUserOperations siptisUserServiceGeneralUserOperations;
    private final RoleService roleService;
    private final SiptisUserRepository siptisUserRepository;
    private final UserInformationService userInformationService;
    private final SiptisUserServiceExistValidation siptisUserServiceExistValidation;
    private final SiptisUserServiceModifyUserOperations siptisUserServiceModifyUserOperations;

    public ServiceAnswer registerAdmin(RegisterAdminDTO dto) {
        ServiceAnswer answer = siptisUserServiceModifyUserOperations.registerUser(dto.getEmail(), dto.getPassword());
        SiptisUser user;
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            user = (SiptisUser) answer.getData();
        } else {
            return answer;
        }
        answer = roleService.getRoleByName(Roles.ADMIN.toString());
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            Role role = (Role) answer.getData();
            user.addRol(role);
        } else {
            return answer;
        }
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.SUCCESSFUL_REGISTER, null);
    }

    public ServiceAnswer getAdminUserList(String search, Pageable pageable) {
        return createResponse(ServiceMessage.OK, siptisUserRepository.searchAdminList(search, pageable));
    }

    public ServiceAnswer adminEditUserInformation(Long id, AdminEditUserInformationDTO dto) {
        Optional<SiptisUser> userOptional = siptisUserRepository.findById(id);
        if (userOptional.isEmpty())
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, null);
        ServiceAnswer answer;
        SiptisUser user = userOptional.get();

        if (!user.getEmail().equals(dto.getEmail()) && (siptisUserServiceExistValidation.existsUserByEmail(dto.getEmail()))) {
            return createResponse(ServiceMessage.EMAIL_ALREADY_EXIST, null);
        }
        UserInformation userInformation = user.getUserInformation();
        if (userInformation == null)
            return createResponse(ServiceMessage.ERROR, null);
        boolean isStudent = false;
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            if (role.getName().equals(Roles.STUDENT.toString()))
                isStudent = true;
        }
        if (isStudent) {
            answer = userInformationService.adminEditStudentInformation(userInformation, dto);
        } else {
            answer = userInformationService.adminEditUserInformation(userInformation, dto);
        }
        if (!answer.getServiceMessage().equals(ServiceMessage.OK))
            return answer;
        user.setEmail(dto.getEmail());
        user.setUserInformation((UserInformation) answer.getData());
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, new UserInformationDTO(user));
    }

    public ServiceAnswer getPossibleTribunals() {
        List<SiptisUser> query = siptisUserRepository.findByRolesName("TRIBUNAL");
        List<TribunalInfoToAssignSection> res = query.stream().map(TribunalInfoToAssignSection::new).toList();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(res).build();
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }
}
