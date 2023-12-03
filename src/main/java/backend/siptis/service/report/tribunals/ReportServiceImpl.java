package backend.siptis.service.report;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.presentations.Presentation;
import backend.siptis.model.entity.presentations.Review;
import backend.siptis.model.entity.project_management.Project;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.presentations.PresentationRepository;
import backend.siptis.model.repository.project_management.ProjectRepository;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.report.geneartion_tools.GeneralTribunalReportTool;
import backend.siptis.service.report.geneartion_tools.PresentationReportTool;
import backend.siptis.service.report.geneartion_tools.ReviewSummaryReportTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final CloudManagementService cloud;
    private final SiptisUserRepository siptisUserRepository;
    private final PresentationRepository presentationRepository;
    private final ProjectRepository projectRepository;

    @Override
    public ServiceAnswer getGeneralTribunalReport() {
        List<SiptisUser> tribunals = siptisUserRepository.findByRoles_NameOrderByUserInformation_LastNamesAsc("TRIBUNAL");
        String fileName = GeneralTribunalReportTool.generateReport(tribunals);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    public ServiceAnswer getReviewSummaryReport(Long presentationId) {
        Optional<Presentation> optionalPresentation = presentationRepository.findByIdAndReviewedTrue(presentationId);
        if (optionalPresentation.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Presentation presentation = optionalPresentation.get();
        String id = "-" + presentation.getProject().getId() + "-";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String specificDate = presentation.getDate().format(dateTimeFormatter);
        List<Review> reviews = presentation.getReviews().stream().toList();
        String fileName = ReviewSummaryReportTool.generateReport(id, specificDate, reviews);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getPresentationReport(Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()) {
            return ServiceAnswer.builder().serviceMessage(ServiceMessage.NOT_FOUND).data(null).build();
        }
        Project project = optionalProject.get();
        List<Presentation> presentations = project.getPresentations().stream().toList();
        String fileName = PresentationReportTool.generateReport(project.getName(), presentations);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }


}




