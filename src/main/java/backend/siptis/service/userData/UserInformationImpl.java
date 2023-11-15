package backend.siptis.service.userData;


import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.userDataDTO.AdminEditUserInformationDTO;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterUserDTO;
import backend.siptis.model.pjo.dto.userDataDTO.UserEditInformationDTO;
import backend.siptis.model.repository.userData.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInformationImpl implements UserInformationService {

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

    private UserInformation findUserInformationById(Long id) {
        return userInformationRepository.findById(id).get();
    }


    @Override
    public ServiceAnswer findById(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(findUserInformationById(id)).build();
    }

    @Override
    public ServiceAnswer registerUserInformation(RegisterStudentDTO dto) {
        dto.setCi(dto.getCi().trim());
        if (existUserByCi(dto.getCi()))
            return createAnswer(ServiceMessage.CI_ALREADY_EXIST, null);
        dto.setCodSIS(dto.getCodSIS().trim());
        if (existUserByCodSIS(dto.getCodSIS()))
            return createAnswer(ServiceMessage.COD_SIS_ALREADY_EXIST, null);

        UserInformation userInformation = new UserInformation();
        userInformation.setNames(dto.getNames().trim());
        userInformation.setLastnames(dto.getLastnames().trim());
        userInformation.setCi(dto.getCi().trim());
        userInformation.setCelNumber(dto.getCelNumber().trim());
        userInformation.setBirthDate(dto.getBirthDate());
        userInformation.setCodSIS(dto.getCodSIS().trim());

        return createAnswer(ServiceMessage.OK, userInformation);
    }

    @Override
    public ServiceAnswer registerUserInformation(RegisterUserDTO dto) {
        dto.setCi(dto.getCi().trim());
        if (existUserByCi(dto.getCi()))
            return createAnswer(ServiceMessage.CI_ALREADY_EXIST, null);

        UserInformation userInformation = new UserInformation();
        userInformation.setNames(dto.getNames().trim());
        userInformation.setLastnames(dto.getLastnames().trim());
        userInformation.setCi(dto.getCi());
        userInformation.setCelNumber(dto.getCelNumber().trim());
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
        dto.setCi(dto.getCi().trim());
        if (!userInformation.getCi().equals(dto.getCi()))
            if (existUserByCi(dto.getCi()))
                return createAnswer(ServiceMessage.CI_ALREADY_EXIST, null);

        userInformation.setLastnames(dto.getLastnames().trim());
        userInformation.setNames(dto.getNames().trim());
        userInformation.setCi(dto.getCi());
        userInformation.setCelNumber(dto.getCelNumber().trim());
        userInformation.setBirthDate(dto.getBirthDate());
        return createAnswer(ServiceMessage.OK, userInformation);
    }


    @Override
    public ServiceAnswer adminEditStudentInformation(UserInformation userInformation, AdminEditUserInformationDTO dto) {
        dto.setCodSIS(dto.getCodSIS().trim());
        if (dto.getCodSIS() == null)
            return createAnswer(ServiceMessage.CODSIS_CANNOT_BE_NULL, null);
        if (dto.getCodSIS().length() > 10 && dto.getCodSIS().length() < 8)
            return createAnswer(ServiceMessage.INVALID_CODSIS_LENGTH, null);

        if (!userInformation.getCodSIS().equals(dto.getCodSIS()))
            if (existUserByCodSIS(dto.getCodSIS()))
                return createAnswer(ServiceMessage.COD_SIS_ALREADY_EXIST, null);
        userInformation.setCodSIS(dto.getCodSIS());
        return adminEditUserInformation(userInformation, dto);

    }


    private ServiceAnswer createAnswer(ServiceMessage message, Object data) {
        return ServiceAnswer.builder().serviceMessage(message)
                .data(data).build();

    }
}
