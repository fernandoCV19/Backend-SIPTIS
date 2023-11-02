package backend.siptis.service.semester;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.semester.EditSemesterInfoDTO;
import backend.siptis.model.pjo.dto.semester.ResponseSemesterInfoDTO;
import backend.siptis.model.pjo.dto.semester.SemesterInformationDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class SemesterInformationServiceTest {
    private final SemesterInformationService semesterInformationService;
    private static SemesterInformationDTO dto = new SemesterInformationDTO();
    private static EditSemesterInfoDTO editDto = new EditSemesterInfoDTO();

    @Autowired
    public SemesterInformationServiceTest(SemesterInformationService semesterInformationService){
        this.semesterInformationService = semesterInformationService;
        dto.setStartDate(LocalDate.of(2023, 04, 12));
        dto.setEndDate(LocalDate.of(2023, 04, 12));
        dto.setPeriod("1-2023");
    }

    @Test
    public void getNoSemesterActiveInformationTest(){
        ServiceAnswer answer = semesterInformationService.getCurrentSemester();
        assertEquals(ServiceMessage.NO_CURRENT_SEMESTER, answer.getServiceMessage());
    }

    @Test
    public void getNoSemesterActivePeriodTest(){
        ServiceAnswer answer = semesterInformationService.getCurrentPeriod();
        assertEquals(ServiceMessage.NO_CURRENT_SEMESTER, answer.getServiceMessage());
    }

    @Test
    public void checkIfExistActiveSemesterFalseTest(){
        ServiceAnswer answer = semesterInformationService.existActiveSemester();
        assertFalse((Boolean) answer.getData());
    }

    @Test
    public void registerSemesterTrueTest(){
        ServiceAnswer answer = semesterInformationService.startSemester(dto);
        assertEquals(ServiceMessage.SEMESTER_STARTED, answer.getServiceMessage());
    }

    @Test
    public void registerSemesterFalseTest(){
        semesterInformationService.startSemester(dto);
        ServiceAnswer answer = semesterInformationService.startSemester(dto);
        assertEquals(ServiceMessage.SEMESTER_ALREADY_EXIST, answer.getServiceMessage());
    }

    @Test
    public void getSemesterActiveInformationTest(){
        semesterInformationService.startSemester(dto);
        ServiceAnswer answer = semesterInformationService.getCurrentSemester();
        assertEquals(ServiceMessage.SEMESTER_INFORMATION, answer.getServiceMessage());
    }

    @Test
    public void getSemesterActivePeriodTest(){
        semesterInformationService.startSemester(dto);
        ServiceAnswer answer = semesterInformationService.getCurrentPeriod();
        assertEquals(ServiceMessage.SEMESTER_INFORMATION, answer.getServiceMessage());
    }

    @Test
    public void editSemesterInformationCorrectTest(){
        semesterInformationService.startSemester(dto);
        ServiceAnswer answer = semesterInformationService.getCurrentSemester();
        ResponseSemesterInfoDTO information = (ResponseSemesterInfoDTO) answer.getData();
        editDto.setStartDate(LocalDate.of(2024, 05, 17));
        editDto.setEndDate(LocalDate.of(2024, 05, 16));
        editDto.setPeriod("1-2024");
        editDto.setId(information.getId());
        ServiceAnswer answer1 = semesterInformationService.editSemester(editDto);
        assertEquals(answer1.getServiceMessage(), ServiceMessage.SEMESTER_DATE_EDITED);
    }

    @Test
    public void checkIfExistActiveSemesterTrueTest(){
        semesterInformationService.startSemester(dto);
        ServiceAnswer answer = semesterInformationService.existActiveSemester();
        assertTrue((Boolean) answer.getData());
    }


}
