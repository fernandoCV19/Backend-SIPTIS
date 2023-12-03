package backend.siptis.service.report.semester_information;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.semester.SemesterInformation;
import backend.siptis.model.repository.semester.SemesterInformationRepository;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.report.semester_information.generation_tools.SemesterInformationReportTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SemesterInformationServiceReportImpl implements SemesterInformationServiceReport {

    private final SemesterInformationRepository semesterInformationRepository;
    private final CloudManagementService cloud;
    @Override
    public ServiceAnswer getAllSemesterInformation(){
        List<SemesterInformation> semesterInformationList = semesterInformationRepository.findAll();
        semesterInformationList = semesterInformationList.stream()
                .sorted((si1, si2) -> si1.getStartDate()
                        .compareTo(si2.getStartDate())).collect(Collectors.toList());
        String fielName = SemesterInformationReportTool.generateReport(semesterInformationList);
        String key = ""; //cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }


}
