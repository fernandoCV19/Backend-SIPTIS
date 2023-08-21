package backend.siptis.service.semester;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.semester.SemesterInformation;
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

        SemesterInformation semester = new SemesterInformation();
        semester.setStartDate(dto.getStartDate());
        semester.setEndDate(dto.getEndDate());
        semester.setInProgress(true);

        repository.save(semester);
        return createResponse(ServiceMessage.SEMESTER_STARTED, "el semestre fue iniciado exitosamente");
    }

    @Override
    public ServiceAnswer getCurrentSemester() {
        return null;
    }

    @Override
    public ServiceAnswer closeSemester(Long id) {
        if(!repository.existsSemesterInformationById(id)){
            return  createResponse(ServiceMessage.ERROR, "No poudimos encontrar el semestre requerido.");
        }
        SemesterInformation semester = repository.findById(id).get();
        semester.setInProgress(false);
        repository.save(semester);
        return createResponse(ServiceMessage.SEMESTER_ENDED, "El semestre fue cerrado correctamente.");
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data){
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data
        ).build();
    }
}
