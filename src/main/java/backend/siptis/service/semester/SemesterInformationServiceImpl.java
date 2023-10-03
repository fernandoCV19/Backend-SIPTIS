package backend.siptis.service.semester;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.semester.SemesterInformation;
import backend.siptis.model.pjo.dto.semester.ResponseSemesterInfoDTO;
import backend.siptis.model.pjo.dto.semester.SemesterInformationDTO;
import backend.siptis.model.repository.semester.SemesterInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SemesterInformationServiceImpl implements SemesterInformationService {

    private final SemesterInformationRepository repository;

    @Override
    public ServiceAnswer startSemester(SemesterInformationDTO dto) {
        //validacion
        if (repository.existsSemesterInformationByInProgressIsTrue()) {
            return createResponse(ServiceMessage.ERROR, "No puede crear un nuevo semestre. Existe uno en curso");
        }

        SemesterInformation semester = new SemesterInformation();
        semester.setStartDate(dto.getStartDate());
        semester.setEndDate(dto.getEndDate());
        semester.setInProgress(true);

        repository.save(semester);
        return createResponse(ServiceMessage.SEMESTER_STARTED, "el semestre fue iniciado exitosamente");
    }

    @Override
    public ServiceAnswer existActiveSemester() {
        return createResponse(ServiceMessage.OK, repository.existsSemesterInformationByInProgressIsTrue());
    }


    @Override
    public ServiceAnswer getCurrentSemester() {
        if (!repository.existsSemesterInformationByInProgressIsTrue()) {
            return createResponse(ServiceMessage.OK, null);
        }
        SemesterInformation semester = repository.findActiveSemester().get();
        ResponseSemesterInfoDTO dto = new ResponseSemesterInfoDTO();
        dto.setId(semester.getId());
        dto.setEndDate(semester.getEndDateString());
        dto.setStartDate(semester.getStartDateString());
        return createResponse(ServiceMessage.OK, dto);
    }

    @Override
    public ServiceAnswer closeSemester(Long id) {
        if (!repository.existsSemesterInformationById(id)) {
            return createResponse(ServiceMessage.ERROR, "No pudimos encontrar el semestre requerido.");
        }
        SemesterInformation semester = repository.findById(id).get();
        semester.setInProgress(false);
        repository.save(semester);
        return createResponse(ServiceMessage.OK, "El semestre fue cerrado correctamente.");
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }
}
