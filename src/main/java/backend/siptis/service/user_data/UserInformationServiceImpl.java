package backend.siptis.service.user_data;


import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.user_data.UserInformation;
import backend.siptis.model.pjo.dto.user_data.AdminEditUserInformationDTO;
import backend.siptis.model.pjo.dto.user_data.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.user_data.RegisterUserDTO;
import backend.siptis.model.pjo.dto.user_data.UserEditInformationDTO;
import backend.siptis.model.repository.user_data.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserInformationServiceImpl implements UserInformationService {

    private final UserInformationRepository userInformationRepository;

    private boolean existUserByCodSIS(String codSIS) {
        return userInformationRepository.existsByCodSIS(codSIS);
    }

    private boolean existUserByCi(String ci) {
        return userInformationRepository.existsByCi(ci);
    }

    private UserInformation findUserInformationById(Long id) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(id);
        return userInformation.orElse(null);
    }

    @Override
    public ServiceAnswer findById(Long id) {
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK)
                .data(findUserInformationById(id)).build();
    }

    @Override
    public ServiceAnswer registerUserInformation(RegisterStudentDTO dto) {
        if (dto == null)
            return createAnswer(ServiceMessage.ERROR, null);
        dto.setCi(dto.getCi().trim());
        if (existUserByCi(dto.getCi()))
            return createAnswer(ServiceMessage.CI_ALREADY_EXIST, null);
        dto.setCodSIS(dto.getCodSIS().trim());
        if (existUserByCodSIS(dto.getCodSIS()))
            return createAnswer(ServiceMessage.COD_SIS_ALREADY_EXIST, null);

        UserInformation userInformation = new UserInformation();
        userInformation.setNames(dto.getNames());
        userInformation.setLastNames(dto.getLastnames());
        userInformation.setFullName(dto.getNames() + " " + dto.getLastnames());
        userInformation.setCi(dto.getCi());
        userInformation.setCelNumber(dto.getCelNumber());
        userInformation.setBirthDate(dto.getBirthDate());
        userInformation.setCodSIS(dto.getCodSIS().trim());

        return createAnswer(ServiceMessage.OK, userInformation);
    }

    @Override
    public ServiceAnswer registerUserInformation(RegisterUserDTO dto) {
        if (dto == null)
            return createAnswer(ServiceMessage.ERROR, null);
        dto.setCi(dto.getCi().trim());
        if (existUserByCi(dto.getCi()))
            return createAnswer(ServiceMessage.CI_ALREADY_EXIST, null);

        UserInformation userInformation = new UserInformation();
        userInformation.setNames(dto.getNames().trim());
        userInformation.setLastNames(dto.getLastnames().trim());
        userInformation.setCi(dto.getCi());
        userInformation.setCelNumber(dto.getCelNumber().trim());
        userInformation.setBirthDate(dto.getBirthDate());

        return createAnswer(ServiceMessage.OK, userInformation);
    }

    @Override
    public ServiceAnswer userEditInformation(UserInformation userInformation, UserEditInformationDTO dto) {
        if (userInformation == null)
            return createAnswer(ServiceMessage.ERROR, null);
        userInformation.setCelNumber(dto.getCelNumber());
        userInformation.setBirthDate(dto.getBirthDate());
        return createAnswer(ServiceMessage.OK, userInformation);
    }

    @Override
    public ServiceAnswer adminEditUserInformation(UserInformation userInformation, AdminEditUserInformationDTO dto) {
        if (userInformation == null)
            return createAnswer(ServiceMessage.ERROR, null);
        dto.setCi(dto.getCi().trim());
        if (!userInformation.getCi().equals(dto.getCi()) && (existUserByCi(dto.getCi()))) {
            return createAnswer(ServiceMessage.CI_ALREADY_EXIST, null);

        }
        userInformation.setLastNames(dto.getLastnames().trim());
        userInformation.setNames(dto.getNames().trim());
        userInformation.setCi(dto.getCi());
        userInformation.setCelNumber(dto.getCelNumber().trim());
        userInformation.setBirthDate(dto.getBirthDate());
        return createAnswer(ServiceMessage.OK, userInformation);
    }


    @Override
    public ServiceAnswer adminEditStudentInformation(UserInformation userInformation, AdminEditUserInformationDTO dto) {
        if (userInformation == null)
            return createAnswer(ServiceMessage.ERROR, null);
        dto.setCodSIS(dto.getCodSIS().trim());
        if (dto.getCodSIS() == null)
            return createAnswer(ServiceMessage.CODSIS_CANNOT_BE_NULL, null);

        if (!userInformation.getCodSIS().equals(dto.getCodSIS()) && (existUserByCodSIS(dto.getCodSIS()))) {
            return createAnswer(ServiceMessage.COD_SIS_ALREADY_EXIST, null);

        }
        userInformation.setCodSIS(dto.getCodSIS());
        return adminEditUserInformation(userInformation, dto);

    }

    private ServiceAnswer createAnswer(ServiceMessage message, Object data) {
        return ServiceAnswer.builder().serviceMessage(message)
                .data(data).build();

    }
}
