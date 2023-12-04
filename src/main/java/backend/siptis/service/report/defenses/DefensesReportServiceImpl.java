package backend.siptis.service.report.defenses;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.defense_management.Defense;
import backend.siptis.model.repository.defense_management.DefenseRepository;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.report.defenses.generation_tools.DefensesReportTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefensesReportServiceImpl implements DefensesReportService {

    private final DefenseRepository defenseRepository;
    private final CloudManagementService cloud;

    @Override
    public ServiceAnswer getDefensesReport() {
        List<Defense> defenses = defenseRepository.findAll();
        String fileName = DefensesReportTool.generateReport(defenses);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }
}
