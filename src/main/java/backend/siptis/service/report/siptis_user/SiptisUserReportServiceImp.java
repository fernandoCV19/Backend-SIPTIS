package backend.siptis.service.report.siptis_user;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.user_data.UserCareer;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.report.siptis_user.generation_tools.AdminReportTool;
import backend.siptis.service.report.siptis_user.generation_tools.GeneralUserReportTool;
import backend.siptis.service.report.siptis_user.generation_tools.StudentReportTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SiptisUserReportServiceImp implements SiptisUserReportService {
    private final SiptisUserRepository siptisUserRepository;
    private final CloudManagementService cloud;

    @Override
    public ServiceAnswer getStudentReport() {
        List<SiptisUser> userList = siptisUserRepository.findByRolesName("STUDENT");
        userList = userList.stream()
                .sorted((si1, si2) -> si1.getUserInformation().getLastNames()
                        .compareTo(si2.getUserInformation().getLastNames())).collect(Collectors.toList());
        List<SiptisUser> sistemas = new ArrayList<>();
        List<SiptisUser> informatica = new ArrayList<>();

        for (SiptisUser user : userList) {
            if (isCareer(user, backend.siptis.commons.UserCareer.SISTEMAS))
                sistemas.add(user);
            else if (isCareer(user, backend.siptis.commons.UserCareer.INFORMATICA))
                informatica.add(user);
        }
        String fileName = StudentReportTool.generateReport(sistemas, informatica);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getAdminReport() {
        List<SiptisUser> userList = siptisUserRepository.findByRolesName("ADMIN");
        userList = userList.stream()
                .sorted((si1, si2) -> si1.getEmail()
                        .compareTo(si2.getEmail())).collect(Collectors.toList());

        String fileName = AdminReportTool.generateReport(userList);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getGeneralUserReport(String role) {
        if (!checkRole(role))
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.ERROR).data(null).build();
        List<SiptisUser> userList = siptisUserRepository.findByRolesName(role.toUpperCase());
        userList = userList.stream()
                .sorted((si1, si2) -> si1.getUserInformation().getLastNames()
                        .compareTo(si2.getUserInformation().getLastNames())).collect(Collectors.toList());
        String fileName = GeneralUserReportTool.generateReport(userList, role.toUpperCase());
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    private boolean checkRole(String role) {
        List<String> roles = List.of("TUTOR", "SUPERVISOR", "TEACHER", "TRIBUNAL", "REVIEWER");

        return roles.contains(role.toUpperCase());
    }

    private boolean isCareer(SiptisUser user, backend.siptis.commons.UserCareer compareCareer) {
        boolean isCareer = true;
        for (UserCareer career : user.getCareer()) {
            isCareer = isCareer && career.getName().equals(compareCareer.name());
        }
        return isCareer;
    }
}
