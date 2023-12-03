package backend.siptis.service.report.tribunals.general_tools;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.defense_management.Defense;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.defense_management.DefenseRepository;
import backend.siptis.model.repository.project_management.ProjectRepository;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.report.geneartion_tools.DefensesReportTool;
import backend.siptis.service.report.geneartion_tools.GeneralTribunalReportTool;
import backend.siptis.service.report.tribunals.GeneralTribunalReportTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final SiptisUserRepository siptisUserRepository;
    private final ProjectRepository projectRepository;
    private final CloudManagementService cloud;
    private final DefenseRepository defenseRepository;

    @Override
    public ServiceAnswer getGeneralTribunalReport() {
        List<SiptisUser> tribunals = siptisUserRepository.findByRoles_NameOrderByUserInformation_LastNamesAsc("TRIBUNAL");
        String fileName = GeneralTribunalReportTool.generateReport(tribunals);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getDefensesReport() {
        List<Defense> defenses = defenseRepository.findAll();
        String fileName = DefensesReportTool.generateReport(defenses);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }


}




