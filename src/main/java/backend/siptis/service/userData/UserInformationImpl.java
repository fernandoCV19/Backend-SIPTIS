package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.*;
import backend.siptis.model.pjo.dto.notifications.PersonalInformationDTO;
import backend.siptis.model.pjo.dto.userDataDTO.AdminEditUserInformationDTO;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterUserDTO;
import backend.siptis.model.pjo.dto.userDataDTO.UserEditInformationDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.GeneralUserPersonalInformationDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterSpecialUserDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.UniversityUserPersonalInformationDTO;
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
    public ServiceAnswer findById(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(findUserInformationById(id)).build();
    }

    @Override
    public ServiceAnswer registerUserInformation(RegisterStudentDTO dto) {
        if(existUserByCi(dto.getCi()))
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, "el documento de identidad ya se encuentra registrado");
        if(existUserByCodSIS(dto.getCodSIS()))
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CODSIS, "el codigo SIS ya se encuentra registrado");

        UserInformation userInformation = new UserInformation();
        userInformation.setNames(dto.getNames());
        userInformation.setLastnames(dto.getLastnames());
        userInformation.setCi(dto.getCi());
        userInformation.setCelNumber(dto.getCelNumber());
        userInformation.setBirthDate(dto.getBirthDate());
        userInformation.setCodSIS(dto.getCodSIS());

        return createAnswer(ServiceMessage.OK, userInformation);
    }

    @Override
    public ServiceAnswer registerUserInformation(RegisterUserDTO dto) {
        if(existUserByCi(dto.getCi()))
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, "el documento de identidad ya se encuentra registrado");

        UserInformation userInformation = new UserInformation();
        userInformation.setNames(dto.getNames());
        userInformation.setLastnames(dto.getLastnames());
        userInformation.setCi(dto.getCi());
        userInformation.setCelNumber(dto.getCelNumber());
        userInformation.setBirthDate(dto.getBirthDate());

        return createAnswer(ServiceMessage.OK, userInformation);
    }

    @Override
    public ServiceAnswer userEditInformation(UserInformation userInformation, UserEditInformationDTO dto) {
        userInformation.setCelNumber(dto.getCelNumber());
        userInformation.setBirthDate(dto.getBirthDate());
        return createAnswer(ServiceMessage.OK, userInformation);
    }

    @Override
    public ServiceAnswer adminEditUserInformation(UserInformation userInformation, AdminEditUserInformationDTO dto) {
        if(!userInformation.getCi().equals(dto.getCi()))
            if(existUserByCi(dto.getCi()))
                return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, "el documento de identidad ya se encuentra registrado");

        userInformation.setLastnames(dto.getLastnames());
        userInformation.setNames(dto.getNames());
        userInformation.setCi(dto.getCi());
        userInformation.setCelNumber(dto.getCelNumber());
        userInformation.setBirthDate(dto.getBirthDate());
        return createAnswer(ServiceMessage.OK, userInformation);
    }
/*
    @Override
    public ServiceAnswer adminEditUserFullInformation(UserInformation userInformation, UniversityUserPersonalInformationDTO dto) {

        if(!userInformation.getCi().equals(dto.getCi()))
            if(existUserByCi(dto.getCi()))
                return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, "el documento de identidad ya se encuentra registrado");

        if(!userInformation.getCodSIS().equals(dto.getCodSIS()))
            if(existUserByCodSIS(dto.getCodSIS()))
                return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CODSIS, "el codigo SIS ya se encuentra registrado");

        userInformation.setLastnames(dto.getLastnames());
        userInformation.setNames(dto.getNames());
        userInformation.setCodSIS(dto.getCodSIS());
        userInformation.setCi(dto.getCi());
        userInformation.setCelNumber(dto.getCelNumber());
        userInformation.setBirthDate(dto.getBirthDate());
        return createAnswer(ServiceMessage.OK, userInformation);
    }

    @Override
    public ServiceAnswer adminEditUserFullInformation(UserInformation userInformation, GeneralUserPersonalInformationDTO dto) {

        if(!userInformation.getCi().equals(dto.getCi()))
            if(existUserByCi(dto.getCi()))
                return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, "el documento de identidad ya se encuentra registrado");

        userInformation.setLastnames(dto.getLastnames());
        userInformation.setNames(dto.getNames());
        userInformation.setCi(dto.getCi());
        userInformation.setCelNumber(dto.getCelNumber());
        userInformation.setBirthDate(dto.getBirthDate());
        return createAnswer(ServiceMessage.OK, userInformation);
    }

    @Override
    public ServiceAnswer searchUserByNameAndRole(String name, Long role_id) {
        List<UserListItemDTO> users =
                userInformationRepository.searchUserByNameAndRole(name, role_id);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(users).build();

    }

*/




    private ServiceAnswer createAnswer(ServiceMessage message, Object data){
        return ServiceAnswer.builder().serviceMessage(message)
                .data(data).build();

    }
}
