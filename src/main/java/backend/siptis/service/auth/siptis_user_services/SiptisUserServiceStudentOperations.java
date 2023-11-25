package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.Roles;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.user_data.UserCareer;
import backend.siptis.model.entity.user_data.UserInformation;
import backend.siptis.model.pjo.dto.user_data.RegisterStudentDTO;
import backend.siptis.model.pjo.dto.user_data.UserInformationDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.user_data.UserCareerRepository;
import backend.siptis.service.auth.RoleService;
import backend.siptis.service.user_data.UserCareerService;
import backend.siptis.service.user_data.UserInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class SiptisUserServiceStudentOperations {

    private final UserInformationService userInformationService;
    private final RoleService roleService;
    private final UserCareerService userCareerService;
    private final SiptisUserRepository siptisUserRepository;
    private final UserCareerRepository userCareerRepository;
    private final SiptisUserServiceExistValidation siptisUserServiceExistValidation;
    private final SiptisUserServiceModifyUserOperations siptisUserServiceModifyUserOperations;

    public ServiceAnswer registerStudent(RegisterStudentDTO dto) {
        ServiceAnswer answer = siptisUserServiceModifyUserOperations.registerUser(dto.getEmail(), dto.getCi());
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
        answer = roleService.getRoleByName(Roles.STUDENT.toString());
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            Role role = (Role) answer.getData();
            user.addRol(role);
        } else {
            return answer;
        }
        answer = userCareerService.getCareerByName(dto.getCareer());
        if (answer.getServiceMessage() == ServiceMessage.OK) {
            UserCareer career = (UserCareer) answer.getData();
            user.addCareer(career);
        } else {
            return answer;
        }
        siptisUserRepository.save(user);
        return createResponse(ServiceMessage.SUCCESSFUL_REGISTER, new UserInformationDTO(user));
    }

    public ServiceAnswer getNumberOfStudentsByYearAndCareer(String careerName) {
        Optional<UserCareer> oUserCareer = userCareerRepository.findByName(careerName.toUpperCase());
        if (oUserCareer.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        return createResponse(ServiceMessage.OK, siptisUserRepository.getNumberOfStudentsByYearAndCareer(oUserCareer.get().getId()));
    }

    public ServiceAnswer getNumberStudentsCareer(String careerName) {
        Optional<UserCareer> oUserCareer = userCareerRepository.findByName(careerName.toUpperCase());
        if (oUserCareer.isEmpty())
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        return createResponse(ServiceMessage.OK, siptisUserRepository.getNumberOfStudentsInCareer(oUserCareer.get().getId()));
    }

    public ServiceAnswer getStudentCareerById(Long id) {
        if (!siptisUserServiceExistValidation.existsUserById(id))
            return createResponse(ServiceMessage.NOT_FOUND, null);
        Optional<SiptisUser> oUser = siptisUserRepository.findById(id);
        if (oUser.isEmpty())
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = oUser.get();
        Set<UserCareer> career = user.getCareer();
        return createResponse(ServiceMessage.OK, career);
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }
}
