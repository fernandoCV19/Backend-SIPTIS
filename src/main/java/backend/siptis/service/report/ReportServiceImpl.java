package backend.siptis.service.report;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.project_management.ProjectRepository;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.report.geneartion_tools.GeneralTribunalReportTool;
import backend.siptis.service.report.geneartion_tools.TribunalProjectReportTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SiptisUserRepository siptisUserRepository;
    private final ProjectRepository projectRepository;
    private final CloudManagementService cloud;

    @Override
    public ServiceAnswer getGeneralTribunalReport() {
        List<SiptisUser> tribunals = siptisUserRepository.findByRoles_NameOrderByUserInformation_LastNamesAsc("TRIBUNAL");
        String fileName = GeneralTribunalReportTool.generateReport(tribunals);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getProjectTribunalReport() {
        List<Project> projects = projectRepository.findByTribunalsNotEmpty();
        String fileName = TribunalProjectReportTool.generateReport(projects);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getProjectCareerReport() {
        return null;
    }
}




