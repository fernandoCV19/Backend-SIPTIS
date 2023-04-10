package backend.siptis.service.records;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.entity.records.GeneralActivity;
import backend.siptis.model.pjo.dto.records.GeneralActivityDTO;
import backend.siptis.model.pjo.vo.GeneralActivityVO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GeneralActivityServiceImplTest {

    private final GeneralActivityService generalActivityService;
    private static final GeneralActivityDTO activityDTO = new GeneralActivityDTO();

    @Autowired
    GeneralActivityServiceImplTest(GeneralActivityService generalActivityService) {
        this.generalActivityService = generalActivityService;
    }

    @BeforeEach
    void setUp() {
        activityDTO.setActivityDate(new Date(2022, 1, 1));
        activityDTO.setGeneralActivityName("test activity");
        activityDTO.setActivityDescription("test activity description");
    }
    @Test
    void findById() {
        GeneralActivityVO generalActivityVO = (GeneralActivityVO) generalActivityService.findById(5).getData();
        assertNotNull(generalActivityVO);
    }
    @Test
    void findNullById() {
        Object generalActivity = generalActivityService.findById(10).getData();
        assertNull(generalActivity);
    }

    @Test
    void findByIdValidatingCorrectNameActivity() {
        GeneralActivityVO generalActivityVO = (GeneralActivityVO) generalActivityService.findById(9).getData();
        String activityName = generalActivityVO.getActivityName();
        assertEquals("Actividad General 1", activityName);
    }
    @Test
    void findByIdValidatingIncorretNameActivity() {
        GeneralActivityVO generalActivityVO = (GeneralActivityVO) generalActivityService.findById(6).getData();
        String activityName = generalActivityVO.getActivityName();
        assertNotEquals("Actividad General 2", activityName);
    }
    @Test
     void findByIdValidatingCorrectDateActivity() {
        GeneralActivityVO generalActivityVO = (GeneralActivityVO) generalActivityService.findById(9).getData();
        Date activityDate = generalActivityVO.getActivityDate();
        assertTrue(activityDate.getDate() == 1 && activityDate.getMonth() == 6 && activityDate.getYear() == 123);
    }

    @Test
    void findByIdValidatingIncorretDateActivity() {
        GeneralActivityVO generalActivityVO = (GeneralActivityVO) generalActivityService.findById(8).getData();
        Date activityDate = generalActivityVO.getActivityDate();
        assertNotEquals(new Date(2023, 6, 2), activityDate);
    }

    @Test
    void findByIdValidatingCorrectDescriptionActivity() {
        GeneralActivityVO generalActivityVO = (GeneralActivityVO) generalActivityService.findById(9).getData();
        String activityDescription = generalActivityVO.getActivityDescription();
        assertEquals("Descripcion 1", activityDescription);
    }

    @Test
    void findByIdValidatingIncorretDescriptionActivity() {
        GeneralActivityVO generalActivityVO = (GeneralActivityVO) generalActivityService.findById(5).getData();
        String activityDescription = generalActivityVO.getActivityDescription();
        assertNotEquals("Descripcion 2", activityDescription);
    }

    @Test
    @Rollback
    void persistGeneralActivity() {
        GeneralActivityVO generalActivity = (GeneralActivityVO) generalActivityService.persistGeneralActivity(activityDTO).getData();
        assertNotNull(generalActivity);
    }

    @Test
    @Rollback
    void persistGeneralActivityValidatingCorrectNameActivity() {
        GeneralActivityVO generalActivity = (GeneralActivityVO) generalActivityService.persistGeneralActivity(activityDTO).getData();
        String activityName = generalActivity.getActivityName();
        assertEquals("test activity", activityName);
    }

    @Test
    @Rollback
    void persistGeneralActivityValidatingIncorrectNameActivity(){
        GeneralActivityVO generalActivity = (GeneralActivityVO) generalActivityService.persistGeneralActivity(activityDTO).getData();

        String activityName = generalActivity.getActivityName();
        assertNotEquals("test activity 2", activityName);
    }
    @Test
    @Rollback
    void persistGeneralActivityValidatingCorrectDateActivity() {
        GeneralActivityVO generalActivity = (GeneralActivityVO) generalActivityService.persistGeneralActivity(activityDTO).getData();
        Date activityDate = generalActivity.getActivityDate();
        assertEquals(new Date(2022, 1, 1), activityDate);
    }
    @Test
    @Rollback
    void persistGeneralActivityValidatingIncorrectDateActivity() {
        GeneralActivityVO generalActivity = (GeneralActivityVO) generalActivityService.persistGeneralActivity(activityDTO).getData();
        Date activityDate = generalActivity.getActivityDate();
        assertNotEquals(new Date(2023, 6, 5), activityDate);
    }
    @Test
    @Rollback
    void persistGeneralActivityValidatingCorrectDescriptionActivity() {
        GeneralActivityVO generalActivity = (GeneralActivityVO) generalActivityService.persistGeneralActivity(activityDTO).getData();
        String activityDescription = generalActivity.getActivityDescription();
        assertEquals("test activity description", activityDescription);
    }
    @Test
    @Rollback
    void persistGeneralActivityValidatingIncorrectDescriptionActivity() {
        GeneralActivityVO generalActivity = (GeneralActivityVO) generalActivityService.persistGeneralActivity(activityDTO).getData();
        String activityDescription = generalActivity.getActivityDescription();
        assertNotEquals("Descripcion 5", activityDescription);
    }
    @Test
    void findAllVOTestCorrectQuantity() {
        assertEquals(5, generalActivityService.findAllVO().size());
    }

    @Test
    void findAllVOTestIncorrectQuantity() {
        assertNotEquals(7, generalActivityService.findAllVO().size());
    }
    @Test
    void findAllVOTestingPaginationCorrectContentSize() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<GeneralActivityVO> page = generalActivityService.findAllVO(pageable);
        assertEquals(2, page.getContent().size());
    }

    @Test
    void findAllVOTestingPaginationIncorrectContentSize() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<GeneralActivityVO> page = generalActivityService.findAllVO(pageable);
        assertNotEquals(3, page.getContent().size());
    }
    @Test
    void findAllVOTestingPaginationCorrectPageNumber() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<GeneralActivityVO> page = generalActivityService.findAllVO(pageable);
        assertEquals(0, page.getNumber());
    }
    @Test
    void findAllVOTestingPaginationIncorrectPageNumber() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<GeneralActivityVO> page = generalActivityService.findAllVO(pageable);
        assertNotEquals(1, page.getNumber());
    }
    @Test
    void findAllVOTestingPaginationCorrectTotalPages() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<GeneralActivityVO> page = generalActivityService.findAllVO(pageable);
        assertEquals(3, page.getTotalPages());
    }
    @Test
    void findAllVOTestingPaginationIncorrectTotalPages() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<GeneralActivityVO> page = generalActivityService.findAllVO(pageable);
        assertNotEquals(4, page.getTotalPages());
    }

    @Test
    @Rollback
    void updateGeneralActivity() {
        activityDTO.setGeneralActivityName("modificacion");
        GeneralActivityVO generalActivity = (GeneralActivityVO) generalActivityService.update(activityDTO, 5).getData();

        assertEquals("modificacion", generalActivity.getActivityName());
    }

    @Test
    @Rollback
    void deleteTestingNull() {
        generalActivityService.delete(5);
        assertNull(generalActivityService.findById(5).getData());
    }
}