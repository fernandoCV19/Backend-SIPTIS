package backend.siptis.service.report.reviewers;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editors_and_reviewers.ProjectSupervisor;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTeacher;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTribunal;
import backend.siptis.model.entity.editors_and_reviewers.ProjectTutor;
import backend.siptis.model.repository.editors_and_reviewers.ProjectSupervisorRepository;
import backend.siptis.model.repository.editors_and_reviewers.ProjectTeacherRepository;
import backend.siptis.model.repository.editors_and_reviewers.ProjectTribunalRepository;
import backend.siptis.model.repository.editors_and_reviewers.ProjectTutorRepository;
import backend.siptis.service.cloud.CloudManagementService;
import backend.siptis.service.report.reviewers.generation_tools.SupervisorProjectReportTool;
import backend.siptis.service.report.reviewers.generation_tools.TeacherProjectReportTool;
import backend.siptis.service.report.reviewers.generation_tools.TribunalProjectReportTool;
import backend.siptis.service.report.reviewers.generation_tools.TutorProjectReportTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewersReportServiceImpl implements ReviewersReportService {

    private final ProjectTribunalRepository projectTribunalRepository;
    private final ProjectTeacherRepository projectTeacherRepository;
    private final ProjectTutorRepository projectTutorRepository;
    private final ProjectSupervisorRepository projectSupervisorRepository;
    private final CloudManagementService cloud;

    @Override
    public ServiceAnswer getTribunalReport(Long tribunalId) {
        List<ProjectTribunal> toReview = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsFalse(tribunalId);
        List<ProjectTribunal> reviewed = projectTribunalRepository.findByTribunalIdAndAcceptedIsFalseAndReviewedIsTrue(tribunalId);
        List<ProjectTribunal> accepted = projectTribunalRepository.findByTribunalIdAndAcceptedIsTrueAndDefensePointsIsNull(tribunalId);
        List<ProjectTribunal> defended = projectTribunalRepository.findByTribunalIdAndDefensePointsIsNotNull(tribunalId);
        String fileName = TribunalProjectReportTool.generateReport(toReview, reviewed, accepted, defended);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getSupervisorReport(Long supervisorId) {
        List<ProjectSupervisor> toReview = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsFalse(supervisorId);
        List<ProjectSupervisor> reviewed = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsFalseAndReviewedIsTrue(supervisorId);
        List<ProjectSupervisor> accepted = projectSupervisorRepository.findBySupervisorIdAndAcceptedIsTrue(supervisorId);
        String fileName = SupervisorProjectReportTool.generateReport(toReview, reviewed, accepted);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getTeacherReport(Long teacherId) {
        List<ProjectTeacher> toReview = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsFalse(teacherId);
        List<ProjectTeacher> reviewed = projectTeacherRepository.findByTeacherIdAndAcceptedIsFalseAndReviewedIsTrue(teacherId);
        List<ProjectTeacher> accepted = projectTeacherRepository.findByTeacherIdAndAcceptedIsTrue(teacherId);
        String fileName = TeacherProjectReportTool.generateReport(toReview, reviewed, accepted);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }

    @Override
    public ServiceAnswer getTutorReport(Long tutorId) {
        List<ProjectTutor> toReview = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsFalse(tutorId);
        List<ProjectTutor> reviewed = projectTutorRepository.findByTutorIdAndAcceptedIsFalseAndReviewedIsTrue(tutorId);
        List<ProjectTutor> accepted = projectTutorRepository.findByTutorIdAndAcceptedIsTrue(tutorId);
        String fileName = TutorProjectReportTool.generateReport(toReview, reviewed, accepted);
        String key = cloud.uploadReportToCloud(fileName);
        return ServiceAnswer.builder().serviceMessage(ServiceMessage.DOCUMENT_GENERATED).data(key).build();
    }
}
