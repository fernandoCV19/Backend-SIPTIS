package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.UserListItemDTO;
import backend.siptis.model.pjo.dto.stadisticsDTO.AdminListItemDTO;
import backend.siptis.model.repository.userData.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInformationImpl implements UserInformationService{

    private final UserInformationRepository userInformationRepository;

    @Override
    public boolean existByCodSIS(String codSIS) {
        return userInformationRepository.existsByCodSIS(codSIS);
    }

    @Override
    public boolean existByCi(String ci) {
        return userInformationRepository.existsByCi(ci);
    }

    @Override
    public boolean existsById(Long id) {
        return userInformationRepository.existsById(id.intValue());
    }

    @Override
    public UserInformation findById(Long id) {

        int userId = id.intValue();
        Optional<UserInformation> optional = userInformationRepository.findById(userId);
        return optional.get();
    }

    @Override
    public ServiceAnswer getAllAdmin() {
        List<AdminListItemDTO> userList =
                userInformationRepository.getAllAdmins();
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userList).build();
    }

    @Override
    public ServiceAnswer getAllStudents() {
        List<UserListItemDTO> userList =
                userInformationRepository.getAllUsersByRole(1L);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userList).build();
    }

    @Override
    public ServiceAnswer getAllTeachers() {
        List<UserListItemDTO> userList =
                userInformationRepository.getAllUsersByRole(3L);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userList).build();
    }

    @Override
    public ServiceAnswer searchUserByNameAndRole(String name, Long role_id) {
        List<UserListItemDTO> users =
                userInformationRepository.searchUserByNameAndRole(name, role_id);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(users).build();

    }
}
