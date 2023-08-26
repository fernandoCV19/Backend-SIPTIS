package backend.siptis.service.userData;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.*;
import backend.siptis.model.pjo.dto.records.PersonalInformationDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.GeneralUserPersonalInformationDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterSpecialUserDTO;
import backend.siptis.model.pjo.dto.usersInformationDTO.RegisterUserDTO;
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
    public ServiceAnswer userEditLimitedInformation(UserInformation userInformation, UserEditPersonalInformationDTO dto) {
        //validaciones
        ServiceAnswer answer;
        userInformation.setCelNumber(dto.getCelNumber());
        userInformation.setBirthDate(dto.getBirthDate());

        return createAnswer(ServiceMessage.OK, userInformation);

    }

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

    @Override
    public ServiceAnswer getUserPersonalInformation(SiptisUser user) {

        UserInformation information = user.getUserInformation();
        if(information == null){
            return ServiceAnswer.builder()
                    .serviceMessage(ServiceMessage.ERROR).data("No se pudo encontrar la información del usuario solicitado.").build();

        }
        PersonalInformationDTO personalInfo = new PersonalInformationDTO();
        personalInfo.setEmail(user.getEmail());

        personalInfo.setNames(information.getNames());
        personalInfo.setLastnames(information.getLastnames());
        personalInfo.setCelNumber(information.getCelNumber());
        personalInfo.setCi(information.getCi());
        personalInfo.setBirthDate(information.getBirthDate());
        personalInfo.setCodSIS(information.getCodSIS());

        return ServiceAnswer.builder()
                .serviceMessage(ServiceMessage.OK).data(personalInfo).build();

    }

    @Override
    public ServiceAnswer registerUserInformation(RegisterUserDTO dto, SiptisUser user) {

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
        userInformation.setSiptisUser(user);

        return createAnswer(ServiceMessage.OK, userInformation);
    }


    @Override
    public ServiceAnswer registerSpecialUserInformation(RegisterSpecialUserDTO dto, SiptisUser user) {

        if(userInformationRepository.existsByCi(dto.getCi()))
            return createAnswer(ServiceMessage.ERROR_REGISTER_ACCOUNT_CI, "el documento de identidad ya se encuentra registrado");

        UserInformation userInformation = new UserInformation();
        userInformation.setNames(dto.getNames());
        userInformation.setLastnames(dto.getLastnames());
        userInformation.setCi(dto.getCi());
        userInformation.setCelNumber(dto.getCelNumber());
        userInformation.setBirthDate(dto.getBirthDate());
        userInformation.setSiptisUser(user);

        return createAnswer(ServiceMessage.OK, userInformation);
    }


    private ServiceAnswer createAnswer(ServiceMessage message, Object data){
        return ServiceAnswer.builder().serviceMessage(message)
                .data(data).build();

    }
}
