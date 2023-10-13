package backend.siptis.service.semester;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
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

    @Autowired
    public SemesterInformationServiceTest(SemesterInformationService semesterInformationService){
        this.semesterInformationService = semesterInformationService;
        dto.setStartDate(LocalDate.of(2023, 04, 12));
        dto.setEndDate(LocalDate.of(2023, 04, 12));
        dto.setPeriod("1-2023");
    }

    @Test
    public void registerSemesterTrue(){
        System.out.println(dto);
        ServiceAnswer answer = semesterInformationService.startSemester(dto);
        assertEquals(answer.getServiceMessage(), ServiceMessage.OK);
    }

    @Test
    public void checkIfExistActiveSemesterTrueTest(){
        semesterInformationService.startSemester(dto);
        ServiceAnswer answer = semesterInformationService.existActiveSemester();
        assertTrue((Boolean) answer.getData());
    }

    @Test
    public void checkIfExistActiveSemesterFalseTest(){
        ServiceAnswer answer = semesterInformationService.existActiveSemester();
        assertFalse((Boolean) answer.getData());
    }
}
