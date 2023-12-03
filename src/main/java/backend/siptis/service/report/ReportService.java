package backend.siptis.service.report;

import backend.siptis.commons.ServiceAnswer;

public interface ReportService {
    ServiceAnswer getGeneralTribunalReport();

    ServiceAnswer getReviewSummaryReport(Long presentationId);

    ServiceAnswer getPresentationReport(Long projectId);
}
