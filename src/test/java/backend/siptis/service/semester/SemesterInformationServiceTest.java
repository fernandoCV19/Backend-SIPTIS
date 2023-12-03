package backend.siptis.service.semester;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.semester.EditSemesterInfoDTO;
import backend.siptis.model.pjo.dto.semester.SemesterInformationDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SemesterInformationServiceTest {
    private static final SemesterInformationDTO dto = new SemesterInformationDTO();
    private static final EditSemesterInfoDTO editDto = new EditSemesterInfoDTO();
    @Autowired
    private SemesterInformationService semesterInformationService;

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
    void givenSemesterInformationDTOWhenCreateSemesterThenServiceMessageSEMESTER_STARTED() {
        ServiceAnswer answer = startSemester();
        assertEquals(ServiceMessage.SEMESTER_STARTED, answer.getServiceMessage());
    }
    @Test
    @Rollback
    @DisplayName("Test for create semester with invalid dates")
    void givenInvalidDatesWhenCreateSemesterThenServiceAnswerERROR_SEMESTER_DATES() {
        dto.setStartDate(LocalDate.of(2023, 04, 11));
        dto.setEndDate(LocalDate.of(2023, 04, 11));
        dto.setPeriod("1-2023");
        ServiceAnswer answer = semesterInformationService.startSemester(dto);
        assertEquals(ServiceMessage.ERROR_SEMESTER_DATES, answer.getServiceMessage());
    }
    @Test
    @Rollback
    @DisplayName("Test for create semester fail")
    void givenSemesterDTO_whenCreateSemester_thenServiceMessageSEMESTER_ALREADY_EXIST() {
        startSemester();
        ServiceAnswer answer = semesterInformationService.startSemester(dto);
        assertEquals(ServiceMessage.SEMESTER_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for edit semester with wrong dates")
    void givenEditSemesterDTO_whenEditSemesterInformation_thenServiceMessageERROR_SEMESTER_DATES() {
        startSemester();
        editSemester();
        editDto.setStartDate(LocalDate.of(2024, 05, 20));
        ServiceAnswer answer = semesterInformationService.editSemester(editDto);
        assertEquals(ServiceMessage.ERROR_SEMESTER_DATES, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for edit semester when no current semester")
    void givenEditSemesterDTO_whenEditSemesterInformation_thenServiceMessageNO_CURRENT_SEMESTER() {
        editSemester();
        ServiceAnswer answer = editSemester();
        assertEquals(ServiceMessage.NO_CURRENT_SEMESTER, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for edit semester id does not exist")
    void givenEditSemesterDTO_whenEditSemesterInformationthenServiceMessageID_DOES_NOT_EXIST() {
        startSemester();
        editSemester();
        editDto.setId(123L);
        ServiceAnswer answer = semesterInformationService.editSemester(editDto);
        assertEquals(ServiceMessage.ID_DOES_NOT_EXIST, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for edit semester id does not exist")
    void givenEditSemesterDTO_whenEditSemesterInformationthenServiceMessageSEMESTER_INFO_EDITED() {
        startSemester();
        editSemester();
        editDto.setId(1L);
        ServiceAnswer answer = semesterInformationService.editSemester(editDto);
        assertEquals(ServiceMessage.SEMESTER_INFO_EDITED, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for check if exist active semester true")
    void givenSemesterInformation_whenExistActiveSemesterthenServiceMessageSEMESTER_INFORMATION() {
        startSemester();
        ServiceAnswer answer = semesterInformationService.existActiveSemester();
        assertEquals(ServiceMessage.SEMESTER_INFORMATION, answer.getServiceMessage());
    }
    @Test
    @DisplayName("Test for check if exist active semester false")
    void givenSemester_whenExistActiveSemester_thenServiceMessageSEMESTER_INFORMATION() {
        ServiceAnswer answer = semesterInformationService.existActiveSemester();
        assertEquals(ServiceMessage.SEMESTER_INFORMATION, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for get current semester information success")
    void getCurrentSemesterInformationSuccessTest() {
        startSemester();
        ServiceAnswer answer = semesterInformationService.getCurrentSemester();
        assertEquals(ServiceMessage.SEMESTER_INFORMATION, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for get current semester information no current semester")
    void getCurrentSemesterInformationFailNoCurrentSemesterTest() {
        ServiceAnswer answer = semesterInformationService.getCurrentSemester();
        assertEquals(ServiceMessage.NO_CURRENT_SEMESTER, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for get current period success")
    void givenSemesterInformation_whenGetCurrentPeriod_thenServiceMessageSEMESTER_INFORMATION() {
        startSemester();
        ServiceAnswer answer = semesterInformationService.getCurrentPeriod();
        assertEquals(ServiceMessage.SEMESTER_INFORMATION, answer.getServiceMessage());
    }

    @Test
    @DisplayName("Test for get current period no current semester")
    void getCurrentPeriodFailNoCurrentSemesterTest() {
        ServiceAnswer answer = semesterInformationService.getCurrentPeriod();
        assertEquals(ServiceMessage.NO_CURRENT_SEMESTER, answer.getServiceMessage());
    }

    @Test
    @Rollback
    @DisplayName("Test for close semester success")
    void closeSemesterSuccessTest() {
        startSemester();
        ServiceAnswer answer = semesterInformationService.closeSemester(1L);
        assertEquals(ServiceMessage.SEMESTER_ENDED, answer.getServiceMessage());
    }

    @Test
    @Rollback
    @DisplayName("Test for close semester no current semester")
    void closeSemesterFailNoCurrentSemesterTest() {
        ServiceAnswer answer = semesterInformationService.closeSemester(1L);
        assertEquals(ServiceMessage.NO_CURRENT_SEMESTER, answer.getServiceMessage());
    }

}
