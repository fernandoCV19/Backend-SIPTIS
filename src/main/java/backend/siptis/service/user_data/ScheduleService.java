package backend.siptis.service.user_data;

import backend.siptis.commons.ServiceAnswer;

public interface ScheduleService {
    ServiceAnswer getAllSchedulesFromAProject(Long projectId);
}

