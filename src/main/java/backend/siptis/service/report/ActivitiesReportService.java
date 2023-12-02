package backend.siptis.service.report;

import backend.siptis.commons.ServiceAnswer;

public interface ActivitiesReportService {
    ServiceAnswer getGeneralActivitesReport();
    ServiceAnswer getActivitiesByProject();
}
