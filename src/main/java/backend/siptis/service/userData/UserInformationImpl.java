package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.*;
import backend.siptis.model.pjo.dto.records.PersonalInformationDTO;
import backend.siptis.model.pjo.dto.stadisticsDTO.AdminListItemDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterUserDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.UserPersonalInformationDTO;
import backend.siptis.model.repository.userData.SiptisUserRepository;
import backend.siptis.model.repository.userData.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserInformationImpl implements UserInformationService{

    private final UserInformationRepository userInformationRepository;


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
    public ServiceAnswer userEditLimitedInformation(UserEditPersonalInformationDTO dto) {
        //validaciones

        return null;
    }

    @Override
    public ServiceAnswer adminEditUserFullInformation(AdminEditUserPersonalInformationDTO dto) {
        return null;
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

    @Override
    public ServiceAnswer registerUserInformation(UserPersonalInformationDTO dto) {

        ServiceAnswer answer = validateInformation(dto);

        if(answer != null){
            return answer;
        }

        UserInformation userInformation = new UserInformation();
        userInformation.setNames(dto.getNames());
        userInformation.setLastnames(dto.getLastnames());
        userInformation.setCi(dto.getCi());
        userInformation.setBirthDate(dto.getBirthDate());
        userInformation.setCodSIS(dto.getCodSIS());

        return createAnswer(ServiceMessage.OK, userInformation);
    }

    @Override
    public ServiceAnswer registerUserInformation(RegisterUserDTO dto){
        ServiceAnswer answer = validateInformation(dto);
        if(answer != null){
            return answer;
        }

        UserInformation userInformation = new UserInformation();
        userInformation.setNames(dto.getNames());
        userInformation.setLastnames(dto.getLastnames());
        userInformation.setCi(dto.getCi());
        userInformation.setBirthDate(dto.getBirthDate());
        userInformation.setCodSIS(dto.getCodSIS());

        return createAnswer(ServiceMessage.OK, userInformation);
    }

    @Override
    public ServiceAnswer registerStudentInformation(StudentRegisterDTO dto) {
        return null;
    }

    @Override
    public ServiceAnswer registerTeacherInformation(TeacherRegisterDTO dto) {
        return null;
    }

    private ServiceAnswer validateInformation(UserPersonalInformationDTO dto){
        String errorMessage = "";
        if(existUserByCi(dto.getCi())){
            errorMessage = "El ci ya se encuentra registrado en el sistema";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        if(existUserByCodSIS(dto.getCodSIS())){
            errorMessage = "El codigoSIS ya se encuentra registrado en el sistema";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CODSIS, errorMessage);
        }

        return null;
    }

    private ServiceAnswer validateInformation(RegisterUserDTO dto){
        String errorMessage = "";
        if(existUserByCi(dto.getCi())){
            errorMessage = "El ci ya se encuentra registrado en el sistema";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, errorMessage);
        }
        if(existUserByCodSIS(dto.getCodSIS())){
            errorMessage = "El codigoSIS ya se encuentra registrado en el sistema";
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CODSIS, errorMessage);
        }

        return null;
    }


    private ServiceAnswer createAnswer(ServiceMessage message, Object data){
        return ServiceAnswer.builder().serviceMessage(message)
                .data(data).build();

    }
}
