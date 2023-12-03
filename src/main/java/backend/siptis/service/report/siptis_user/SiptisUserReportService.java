package backend.siptis.service.report.siptis_user;

import backend.siptis.commons.ServiceAnswer;

public interface SiptisUserReportService {

    ServiceAnswer getStudentReport();

    ServiceAnswer getAdminReport();

    ServiceAnswer getGeneralUserReport(String role);

}
