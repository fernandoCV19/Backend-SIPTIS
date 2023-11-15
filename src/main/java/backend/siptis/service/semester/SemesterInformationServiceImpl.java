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

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class SemesterInformationServiceImpl implements SemesterInformationService {

    private final SemesterInformationRepository repository;

    @Override
    public ServiceAnswer startSemester(SemesterInformationDTO dto) {
        LocalDate endDate = dto.getEndDate();
        LocalDate startDate = dto.getStartDate();
        if (Period.between(startDate, endDate).isNegative() || Period.between(startDate, endDate).isZero()) {
            return createResponse(ServiceMessage.ERROR_SEMESTER_DATES, null);
        }
        if (repository.existsSemesterInformationByInProgressIsTrue())
            return createResponse(ServiceMessage.SEMESTER_ALREADY_EXIST, null);
        SemesterInformation semester = new SemesterInformation();
        semester.setStartDate(dto.getStartDate());
        semester.setEndDate(dto.getEndDate());
        semester.setPeriod(dto.getPeriod());
        semester.setInProgress(true);

        repository.save(semester);
        return createResponse(ServiceMessage.SEMESTER_STARTED, semester);
    }

    @Override
    public ServiceAnswer editSemester(EditSemesterInfoDTO dto) {
        LocalDate endDate = dto.getEndDate();
        LocalDate startDate = dto.getStartDate();
        if (Period.between(startDate, endDate).isNegative() || Period.between(startDate, endDate).isZero()) {
            return createResponse(ServiceMessage.ERROR_SEMESTER_DATES, null);
        }
        if (!repository.existsSemesterInformationByInProgressIsTrue())
            return createResponse(ServiceMessage.NO_CURRENT_SEMESTER, null);
        if (!repository.existsById(dto.getId()))
            return createResponse(ServiceMessage.ID_DOES_NOT_EXIST, null);
        SemesterInformation semesterInformation = repository.findFirstByInProgressTrueAndIdOrderByEndDateDesc(dto.getId()).get();
        if (semesterInformation == null)
            return createResponse(ServiceMessage.NO_CURRENT_SEMESTER, null);
        semesterInformation.setStartDate(dto.getStartDate());
        semesterInformation.setEndDate(dto.getEndDate());
        semesterInformation.setPeriod(dto.getPeriod());
        semesterInformation.setInProgress(true);

        repository.save(semesterInformation);
        return createResponse(ServiceMessage.SEMESTER_INFO_EDITED, semesterInformation);
    }

    @Override
    public ServiceAnswer existActiveSemester() {
        return createResponse(ServiceMessage.SEMESTER_INFORMATION, repository.existsSemesterInformationByInProgressIsTrue());
    }


    @Override
    public ServiceAnswer getCurrentSemester() {
        if (!repository.existsSemesterInformationByInProgressIsTrue())
            return createResponse(ServiceMessage.NO_CURRENT_SEMESTER, null);
        SemesterInformation semester = repository.findFirstByInProgressTrueOrderByEndDateDesc().get();
        ResponseSemesterInfoDTO dto = new ResponseSemesterInfoDTO();
        dto.setId(semester.getId());
        dto.setEndDate(semester.getEndDateString());
        dto.setStartDate(semester.getStartDateString());
        dto.setPeriod(semester.getPeriod());
        return createResponse(ServiceMessage.SEMESTER_INFORMATION, dto);
    }

    @Override
    public ServiceAnswer getCurrentPeriod() {
        if (!repository.existsSemesterInformationByInProgressIsTrue())
            return createResponse(ServiceMessage.NO_CURRENT_SEMESTER, null);
        SemesterInformation semester = repository.findFirstByInProgressTrueOrderByEndDateDesc().get();
        return createResponse(ServiceMessage.SEMESTER_INFORMATION, semester.getPeriod());

    }

    @Override
    public ServiceAnswer closeSemester(Long id) {
        if (!repository.existsSemesterInformationById(id)) {
            return createResponse(ServiceMessage.NO_CURRENT_SEMESTER, null);
        }
        SemesterInformation semester = repository.findById(id).get();
        if (!semester.isInProgress()) {
            return createResponse(ServiceMessage.NO_CURRENT_SEMESTER, null);
        }
        semester.setInProgress(false);
        repository.save(semester);
        return createResponse(ServiceMessage.SEMESTER_ENDED, semester);
    }

    private ServiceAnswer createResponse(ServiceMessage serviceMessage, Object data) {
        return ServiceAnswer.builder().serviceMessage(
                serviceMessage).data(data).build();
    }
}
