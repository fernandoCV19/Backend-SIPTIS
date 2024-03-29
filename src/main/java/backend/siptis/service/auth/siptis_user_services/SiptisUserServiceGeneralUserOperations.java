package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.notifications.Activity;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.entity.user_data.UserArea;
import backend.siptis.model.pjo.dto.user_data.UserInformationDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Transactional
@RequiredArgsConstructor
@Service
public class SiptisUserServiceGeneralUserOperations {

    private final SiptisUserRepository siptisUserRepository;
    private final SiptisUserServiceExistValidation siptisUserServiceExistValidation;

    public Long getProjectById(Long id) {
        Optional<Project> project = siptisUserRepository.findProjectById(id);
        return project.map(Project::getId).orElse(null);
    }

    public ServiceAnswer getAllUsers() {
        List<SiptisUser> userList = siptisUserRepository.findAll();
        return userList.isEmpty() ?
                ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(userList).build() :
                ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(siptisUserRepository.findAll()).build();
    }

    public ServiceAnswer getUserPersonalInformation(Long id) {
        if (!siptisUserServiceExistValidation.existsUserById(id))
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = findUserById(id);
        return createResponse(ServiceMessage.OK, new UserInformationDTO(user));
    }

    public ServiceAnswer getUserAreasById(Long id) {
        Optional<SiptisUser> userOptional = siptisUserRepository.findById(id);
        if (userOptional.isEmpty())
            return createResponse(ServiceMessage.NOT_FOUND, null);
        SiptisUser user = userOptional.get();
        Set<UserArea> areas = user.getAreas();
        return createResponse(ServiceMessage.OK, areas);
    }

    public ServiceAnswer getUserList(String search, String role, Pageable pageable) {
        return createResponse(ServiceMessage.OK, siptisUserRepository.searchUserList(search, role, pageable));
    }

    public ServiceAnswer getNormalUserList(String search, Pageable pageable) {
        return createResponse(ServiceMessage.OK, siptisUserRepository.searchNormalUserList(search, pageable));
    }

    public ServiceAnswer getPersonalActivities(Long id, Pageable pageable) {
        LocalDateTime now = LocalDateTime.now();
        Date actual = new Date(now.getYear() - 1900, now.getMonthValue() - 1, now.getDayOfMonth() - 1);

        Page<Activity> activities = siptisUserRepository.findAllPersonalActivities(id, actual, pageable);

        return ServiceAnswer.builder().serviceMessage(ServiceMessage.OK).data(activities).build();
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
