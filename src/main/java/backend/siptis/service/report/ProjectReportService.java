package backend.siptis.service.report;

import backend.siptis.commons.ServiceAnswer;

public interface ProjectReportService {
    ServiceAnswer getProjectTribunalReport();

    ServiceAnswer getProjectCareerReport();
}
