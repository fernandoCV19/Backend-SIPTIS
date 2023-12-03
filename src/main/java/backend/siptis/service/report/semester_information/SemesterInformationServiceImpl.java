package backend.siptis.service.report.semester_information;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.semester.SemesterInformation;
import backend.siptis.model.repository.semester.SemesterInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterInformationServiceImpl implements SemesterInformationService{

    private final SemesterInformationRepository semesterInformationRepository;
    @Override
    public ServiceAnswer getAllSemesterInformation(){
        List<SemesterInformation> semesterInformationList = semesterInformationRepository.findAll();

    }


}
