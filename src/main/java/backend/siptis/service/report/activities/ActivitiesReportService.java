package backend.siptis.service.report.activities;

import backend.siptis.commons.ServiceAnswer;

public interface ActivitiesReportService {
    ServiceAnswer getGeneralActivitesReport();
    ServiceAnswer getActivitiesByProject();
}
