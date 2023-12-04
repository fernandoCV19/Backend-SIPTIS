package backend.siptis.service.report.presentation;

import backend.siptis.commons.ServiceAnswer;

public interface PresentationReportService {

    ServiceAnswer getReviewSummaryReport(Long presentationId);

    ServiceAnswer getPresentationReport(Long projectId);
}
