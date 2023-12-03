package backend.siptis.service.report.projects;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectReportService {
    ServiceAnswer getProjectTribunalReport();

    ServiceAnswer getProjectCareerReport();

    ServiceAnswer getProjectAreaReport();

    ServiceAnswer getCompleteProjectReport();
}
