package backend.siptis.service.semester;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.semester.SemesterInformation;
import backend.siptis.model.pjo.dto.semester.EditSemesterInfoDTO;
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
        if (repository.existsSemesterInformationByInProgressIsTrue())
            return createResponse(ServiceMessage.ERROR, "No puede crear un nuevo semestre. Existe uno en curso.");

        SemesterInformation semester = new SemesterInformation();
        semester.setStartDate(dto.getStartDate());
        semester.setEndDate(dto.getEndDate());
        semester.setPeriod(dto.getPeriod());
        semester.setInProgress(true);

        repository.save(semester);
        return createResponse(ServiceMessage.SEMESTER_STARTED, "El semestre fue iniciado exitosamente");
    }

    @Override
    public ServiceAnswer editSemester(EditSemesterInfoDTO dto) {
        if (!repository.existsSemesterInformationByInProgressIsTrue())
            return createResponse(ServiceMessage.ERROR, "No existe un semestre en curso.");
        if (!repository.existsById(dto.getId()))
            return createResponse(ServiceMessage.ERROR, "No existe el semestre solicitado.");
        SemesterInformation semesterInformation = repository.findActiveSemesterById(dto.getId()).get();
        if (semesterInformation == null)
            return createResponse(ServiceMessage.ERROR, "Lo sentimos, no puede modificar la información del semestre.");
        // SemesterInformation semester = new SemesterInformation();
        semesterInformation.setStartDate(dto.getStartDate());
        semesterInformation.setEndDate(dto.getEndDate());
        semesterInformation.setPeriod(dto.getPeriod());
        semesterInformation.setInProgress(true);

        repository.save(semesterInformation);
        return createResponse(ServiceMessage.SEMESTER_STARTED, "El semestre fue modificado exitosamente");
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
        dto.setPeriod(semester.getPeriod());
        return createResponse(ServiceMessage.OK, dto);
    }

    @Override
    public ServiceAnswer getCurrentPeriod() {
        if (!repository.existsSemesterInformationByInProgressIsTrue())
            return createResponse(ServiceMessage.ERROR, "No existe un semestre en curso.");

        SemesterInformation semester = repository.findActiveSemester().get();
        return createResponse(ServiceMessage.OK, semester.getPeriod());

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
