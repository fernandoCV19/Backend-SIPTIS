package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;

public interface ScheduleService {
    ServiceAnswer getAllSchedulesFromAProject(Long projectId);
}

