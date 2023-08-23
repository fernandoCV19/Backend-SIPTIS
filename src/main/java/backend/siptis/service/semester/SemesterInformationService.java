package backend.siptis.service.semester;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.semester.SemesterInformationDTO;

import java.text.ParseException;

public interface SemesterInformationService {

    ServiceAnswer startSemester(SemesterInformationDTO dto);

    ServiceAnswer existActiveSemester();

    ServiceAnswer getCurrentSemester();

    ServiceAnswer closeSemester(Long id);
}
