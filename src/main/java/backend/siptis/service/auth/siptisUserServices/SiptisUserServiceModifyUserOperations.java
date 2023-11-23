package backend.siptis.service.auth.siptisUserServices;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.userData.UserArea;
import backend.siptis.model.entity.userData.UserInformation;
import backend.siptis.model.pjo.dto.userDataDTO.RegisterUserDTO;
import backend.siptis.model.pjo.dto.userDataDTO.UserEditInformationDTO;
import backend.siptis.model.pjo.dto.userDataDTO.UserInformationDTO;
import backend.siptis.model.pjo.dto.userDataDTO.UserSelectedAreasDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.service.userData.UserAreaService;
import backend.siptis.service.userData.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class SiptisUserServiceModifyUserOperations {

    private final SiptisUserRepository siptisUserRepository;
    private final UserInformationService userInformationService;
    private final SiptisUserServiceExistValidation siptisUserServiceExistValidation;
    private final UserAreaService userAreaService;

    public ServiceAnswer updateAreas(Long id, UserSelectedAreasDTO dto) {
        if (!siptisUserServiceExistValidation.existsUserById(id))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, null);
        List<Long> ids = dto.getIds();
        Set<UserArea> areas = new HashSet<>();
        for (Long areaId : ids) {
            if (!userAreaService.userAreaExistById(areaId.intValue()))
                return createResponse(ServiceMessage.NOT_FOUND, null);
            areas.add(userAreaService.getUserAreaById(areaId.intValue()));
        }
        Optional<SiptisUser> oUser = siptisUserRepository.findById(id);
        if (oUser.isEmpty())
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = oUser.get();
        user.setAreas(areas);
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, user.getAreas());
    }

    public ServiceAnswer userEditPersonalInformation(Long id, UserEditInformationDTO dto) {
        Optional<SiptisUser> userOptional = siptisUserRepository.findById(id);
        if (userOptional.isEmpty())
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = userOptional.get();
        ServiceAnswer answer;
        if (!user.getEmail().equals(dto.getEmail()) && (siptisUserServiceExistValidation.existsUserByEmail(dto.getEmail())))
            return createResponse(ServiceMessage.EMAIL_ALREADY_EXIST, null);
        UserInformation userInformation = user.getUserInformation();
        answer = userInformationService.userEditInformation(userInformation, dto);
        if (!answer.getServiceMessage().equals(ServiceMessage.OK))
            return answer;
        user.setEmail(dto.getEmail());
        user.setUserInformation((UserInformation) answer.getData());
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.OK, new UserInformationDTO(user));
    }

    public ServiceAnswer registerUser(RegisterUserDTO dto) {
        ServiceAnswer answer = registerUser(dto.getEmail(), dto.getCi());
        SiptisUser user;
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            user = (SiptisUser) answer.getData();
        } else {
            return answer;
        }
        answer = userInformationService.registerUserInformation(dto);
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            UserInformation information = (UserInformation) answer.getData();
            user.setUserInformation(information);
            information.setSiptisUser(user);
        } else {
            return answer;
        }
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.SUCCESSFUL_REGISTER, new UserInformationDTO(user));
    }

    public ServiceAnswer registerUser(String email, String password) {
        if (email.equals(""))
            return createResponse(ServiceMessage.ERROR, null);
        email = email.trim();
        if (siptisUserServiceExistValidation.existsUserByEmail(email))
            return createResponse(ServiceMessage.EMAIL_ALREADY_EXIST, null);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String userPassword = encoder.encode(password);
        return createResponse(ServiceMessage.OK, new SiptisUser(email, userPassword));
    }

    private SiptisUser findUserById(long id) {
        Optional<SiptisUser> userOptional = siptisUserRepository.findById(id);
        return userOptional.orElse(null);
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }
}
