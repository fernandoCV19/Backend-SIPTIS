package backend.siptis.service.report.tribunals;

import backend.siptis.commons.ServiceAnswer;

public interface ReportService {
    ServiceAnswer getGeneralTribunalReport();

    ServiceAnswer getReviewSummaryReport(Long presentationId);

    ServiceAnswer getPresentationReport(Long projectId);
}
