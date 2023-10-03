package backend.siptis.service.semester;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.semester.SemesterInformationDTO;

public interface SemesterInformationService {

    ServiceAnswer startSemester(SemesterInformationDTO dto);

    ServiceAnswer existActiveSemester();

    ServiceAnswer getCurrentSemester();

    ServiceAnswer closeSemester(Long id);
}
