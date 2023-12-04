package backend.siptis.service.report.reviewers;

import backend.siptis.commons.ServiceAnswer;

public interface ReviewersReportService {

    ServiceAnswer getTribunalReport(Long tribunalId);

    ServiceAnswer getSupervisorReport(Long supervisorId);

    ServiceAnswer getTeacherReport(Long teacherId);

    ServiceAnswer getTutorReport(Long tutorId);
}
