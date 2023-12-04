package backend.siptis.service.report.tribunals;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.report.tribunals.generation_tools.TribunalsReportTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TribunalsReportServiceImpl implements TribunalsReportService {

    private final SiptisUserRepository siptisUserRepository;
    private final CloudManagementService cloud;

    @Override
    public ServiceAnswer getTribunals() {
        List<SiptisUser> tribunals = siptisUserRepository.findByRolesName("TRIBUNAL");
        String fileName = TribunalsReportTool.generateReport(tribunals);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }
}
