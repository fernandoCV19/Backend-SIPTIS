package backend.siptis.service.semester;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.semester.EditSemesterInfoDTO;
import backend.siptis.model.pjo.dto.semester.SemesterInformationDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class SemesterInformationServiceTest {
    private static final SemesterInformationDTO dto = new SemesterInformationDTO();
    private static final EditSemesterInfoDTO editDto = new EditSemesterInfoDTO();
    private final SemesterInformationService semesterInformationService;

    @Autowired
    public SemesterInformationServiceTest(SemesterInformationService semesterInformationService) {
        this.semesterInformationService = semesterInformationService;
    }

    private ServiceAnswer startSemester() {
        dto.setStartDate(LocalDate.of(2023, 04, 11));
        dto.setEndDate(LocalDate.of(2023, 05, 11));
        dto.setPeriod("1-2023");
        return semesterInformationService.startSemester(dto);
    }

    private ServiceAnswer editSemester() {
        editDto.setStartDate(LocalDate.of(2024, 05, 17));
        editDto.setEndDate(LocalDate.of(2024, 05, 20));
        editDto.setPeriod("1-2024");
        editDto.setId(1L);
        return semesterInformationService.editSemester(editDto);
    }

    @Test
    @Rollback
    @DisplayName("Test for create semester")
    public void givenSemesterInformationDTOWhenCreateSemesterThenServiceMessageSEMESTER_STARTED() {
        ServiceAnswer answer = startSemester();
        assertEquals(ServiceMessage.SEMESTER_STARTED, answer.getServiceMessage());
    }

    @Test
    @Rollback
    @DisplayName("Test for create semester with invalid dates")
    public void givenInvalidDatesWhenCreateSemesterThenServiceAnswerERROR_SEMESTER_DATES() {
        dto.setStartDate(LocalDate.of(2023, 04, 11));
        dto.setEndDate(LocalDate.of(2023, 04, 11));
        dto.setPeriod("1-2023");
        ServiceAnswer answer = semesterInformationService.startSemester(dto);
        assertEquals(ServiceMessage.ERROR_SEMESTER_DATES, answer.getServiceMessage());
    }

    @Test
    @Rollback
    public void createSemesterFailSemesterAlreadyExistTest() {
        startSemester();
        ServiceAnswer answer = semesterInformationService.startSemester(dto);
        assertEquals(ServiceMessage.SEMESTER_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    @Rollback
    public void editSemesterInformationFailInvalidDatesTest() {
        startSemester();
        editSemester();
        editDto.setStartDate(LocalDate.of(2024, 05, 20));
        ServiceAnswer answer = semesterInformationService.editSemester(editDto);
        assertEquals(ServiceMessage.ERROR_SEMESTER_DATES, answer.getServiceMessage());
    }

    @Test
    @Rollback
    public void editSemesterInformationFailNoCurrentSemesterTest() {
        editSemester();
        ServiceAnswer answer = editSemester();
        assertEquals(ServiceMessage.NO_CURRENT_SEMESTER, answer.getServiceMessage());
    }

    @Test
    @Rollback
    public void editSemesterInformationFailWrongIdTest() {
        startSemester();
        editSemester();
        editDto.setId(123L);
        ServiceAnswer answer = semesterInformationService.editSemester(editDto);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }

    @Test
    @Rollback
    public void existActiveSemesterSuccessTest() {
        startSemester();
        ServiceAnswer answer = semesterInformationService.existActiveSemester();
        assertEquals(ServiceMessage.SEMESTER_INFORMATION, answer.getServiceMessage());
    }

    @Test
    @Rollback
    public void existActiveSemesterFailTest() {
        ServiceAnswer answer = semesterInformationService.existActiveSemester();
        assertEquals(ServiceMessage.SEMESTER_INFORMATION, answer.getServiceMessage());
    }

    @Test
    @Rollback
    public void getCurrentSemesterInformationSuccessTest() {
        startSemester();
        ServiceAnswer answer = semesterInformationService.getCurrentSemester();
        assertEquals(ServiceMessage.SEMESTER_INFORMATION, answer.getServiceMessage());
    }

    @Test
    @Rollback
    public void getCurrentSemesterInformationFailNoCurrentSemesterTest() {
        ServiceAnswer answer = semesterInformationService.getCurrentSemester();
        assertEquals(ServiceMessage.NO_CURRENT_SEMESTER, answer.getServiceMessage());
    }

    @Test
    @Rollback
    public void getCurrentPeriodSuccessTest() {
        startSemester();
        ServiceAnswer answer = semesterInformationService.getCurrentPeriod();
        assertEquals(ServiceMessage.SEMESTER_INFORMATION, answer.getServiceMessage());
    }

    @Test
    @Rollback
    public void getCurrentPeriodFailNoCurrentSemesterTest() {
        ServiceAnswer answer = semesterInformationService.getCurrentPeriod();
        assertEquals(ServiceMessage.NO_CURRENT_SEMESTER, answer.getServiceMessage());
    }

    @Test
    @Rollback
    public void closeSemesterSuccessTest() {
        startSemester();
        ServiceAnswer answer = semesterInformationService.closeSemester(1L);
        assertEquals(ServiceMessage.NO_CURRENT_SEMESTER, answer.getServiceMessage());
    }

    @Test
    @Rollback
    public void closeSemesterFailNoCurrentSemesterTest() {
        ServiceAnswer answer = semesterInformationService.closeSemester(1L);
        assertEquals(ServiceMessage.NO_CURRENT_SEMESTER, answer.getServiceMessage());
    }

}
