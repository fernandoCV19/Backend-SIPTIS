package backend.siptis.service.userData.searchUsers;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.UserListItemDTO;
import backend.siptis.model.pjo.dto.stadisticsDTO.AdminListItemDTO;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.model.repository.userData.UserInformationRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SearchUsersImpl implements SearchUsers {

    @Autowired
    private final SiptisUserRepository siptisUserRepository;
    @Autowired
    private final UserInformationRepository userInformationRepository;

    @Override
    public ServiceAnswer getAllUsers() {
        List<SiptisUser> userList = siptisUserRepository.findAll();

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userList).build();
    }

    @Override
    public ServiceAnswer getAllAdmin() {
        List<AdminListItemDTO> userList =
                userInformationRepository.getAllAdmins();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userList).build();
    }

    @Override
    public ServiceAnswer getAllStudent() {
        List<UserListItemDTO> userList =
                userInformationRepository.getAllUsersByRole(1L);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userList).build();
    }

    @Override
    public ServiceAnswer getAllTeacher() {
        List<UserListItemDTO> userList =
                userInformationRepository.getAllUsersByRole(3L);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userList).build();
    }

    @Override
    public ServiceAnswer searchStudentsByName() {
        return null;
    }

    @Override
    public ServiceAnswer searchStudentsByCodSis() {
        return null;
    }

    @Override
    public ServiceAnswer searchStudentsByEmail() {
        return null;
    }

    @Override
    public ServiceAnswer searchTeachersByName() {
        return null;
    }

    @Override
    public ServiceAnswer searchTeachersByCodSis() {
        return null;
    }

    @Override
    public ServiceAnswer searchTeachersByEmail() {
        return null;
    }

    @Override
    public ServiceAnswer searchAdminByEmail() {
        return null;
    }

    @Override
    public ServiceAnswer searchUserByNameAndRole(String name, Long role_id) {
        List<UserListItemDTO> users =
                userInformationRepository.searchUserByNameAndRole(name, role_id);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(users).build();

    }
}
