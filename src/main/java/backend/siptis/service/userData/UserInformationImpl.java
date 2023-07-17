package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.entity.userData.UserCareer;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.UserListItemDTO;
import backend.siptis.model.pjo.dto.records.PersonalInformationDTO;
import backend.siptis.model.pjo.dto.stadisticsDTO.AdminListItemDTO;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.model.repository.userData.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserInformationImpl implements UserInformationService{

    private final UserInformationRepository userInformationRepository;
    private final SiptisUserRepository siptisUserRepository;


    private boolean existUserByCodSIS(String codSIS) {
        return userInformationRepository.existsByCodSIS(codSIS);
    }

    private boolean existUserByCi(String ci) {
        return userInformationRepository.existsByCi(ci);
    }

    private boolean existsUserById(Long id) {
        return userInformationRepository.existsById(id.intValue());
    }

    private UserInformation findUserInformationById(Long id){
        return userInformationRepository.findById(id).get();
    }

    @Override
    public ServiceAnswer existByCodSIS(String codSIS) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(existUserByCodSIS(codSIS)).build();
    }

    @Override
    public ServiceAnswer existByCi(String ci) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(existUserByCi(ci)).build();
    }

    @Override
    public ServiceAnswer existsById(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(existsUserById(id)).build();
    }

    @Override
    public ServiceAnswer findById(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(findUserInformationById(id)).build();
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

    @Override
    public ServiceAnswer getUserPersonalInformation(SiptisUser user) {
        PersonalInformationDTO personalInfo = new PersonalInformationDTO();
        if(user != null){

            personalInfo.setEmail(user.getEmail());
            UserInformation information = user.getUserInformation();;
            if(information != null){
                personalInfo.setNames(information.getNames());
                personalInfo.setLastnames(information.getLastnames());
                personalInfo.setCelNumber(information.getCelNumber());
                personalInfo.setCi(information.getCi());
                personalInfo.setBirthDate(information.getBirthDate());
                personalInfo.setCodSIS(information.getCodSIS());
            }
        }
        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK).data(personalInfo).build();

    }


    @Override
    public ServiceAnswer getTeacherNotSelectedAreasById(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(userInformationRepository.getNotSelectedAreas(id)).build();

    }


}
