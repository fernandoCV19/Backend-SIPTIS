package backend.siptis.service.notifications;

import backend.siptis.model.pjo.dto.notifications.ActivityDTO;
import backend.siptis.model.pjo.vo.ActivityVO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class ActivityServiceImplTest {

    private final ActivityService activityService;
    private static ActivityDTO activityDTO = new ActivityDTO();
    @Autowired
    ActivityServiceImplTest(ActivityService activityService) {
        this.activityService = activityService;
    }
    @BeforeEach
    void setUp() {
        activityDTO.setActivityDate(new Date(2022, 1, 1));
        activityDTO.setActivityName("test activity");
        activityDTO.setActivityDescription("test activity description");
        activityDTO.setIdProject(1);
    }
    @Test
    void findById() {
        ActivityVO activityVO = (ActivityVO) activityService.findById(1).getData();
        assertNotNull(activityVO);
    }
    @Test
    void findNullById(){
        ActivityVO activityVO = (ActivityVO) activityService.findById(100).getData();
        assertNull(activityVO);
    }
    @Test
    void findByIdValidatingCorrectNameActivity(){
        ActivityVO activityVO = (ActivityVO) activityService.findById(1).getData();
        assertEquals("Actividad 1", activityVO.getActivityName());
    }
    @Test
    void findByIdValidatingIncorrectNameActivity(){
        ActivityVO activityVO = (ActivityVO) activityService.findById(1).getData();
        assertNotEquals("Actividad 2", activityVO.getActivityName());
    }

    @Test
    void findByIdValidatingCorrectDescriptionActivity(){
        ActivityVO activityVO = (ActivityVO) activityService.findById(1).getData();
        assertEquals("Descripcion 1", activityVO.getActivityDescription());
    }
    @Test
    void findByIdValidatingIncorrectDescriptionActivity(){
        ActivityVO activityVO = (ActivityVO) activityService.findById(1).getData();
        assertNotEquals("Descripcion 2", activityVO.getActivityName());
    }
    @Test
    void findByIdValidatingCorrectIdProject(){
        ActivityVO activityVO = (ActivityVO) activityService.findById(1).getData();
        assertEquals(1, activityVO.getProject().getId());
    }
    @Test
    void findByIdValidatingIncorrectIdProject(){
        ActivityVO activityVO = (ActivityVO) activityService.findById(1).getData();
        assertNotEquals(2, activityVO.getProject().getId());
    }
    @Test
    void findByIdValidatingCorrectDateActivity(){
        ActivityVO activityVO = (ActivityVO) activityService.findById(1).getData();
        Date date = activityVO.getActivityDate();
        assertTrue(date.getDate()== 1 && date.getMonth() == 7 && date.getYear() == 123);
    }
    @Test
    void findByIdValidatingIncorrectDateActivity(){
        ActivityVO activityVO = (ActivityVO) activityService.findById(1).getData();
        assertNotEquals(new Date(2023, 7, 2), activityVO.getActivityDate());
    }
    @Test
    @Rollback
    void persistNotNullActivity() {
        ActivityVO activityVO = (ActivityVO) activityService.persistActivity(activityDTO).getData();
        assertNotNull(activityVO);
    }

    @Test
    @Rollback
    void persistActivityValidatingCorrectNameActivity(){
        ActivityVO activityVO = (ActivityVO) activityService.persistActivity(activityDTO).getData();
        assertEquals("test activity", activityVO.getActivityName());
    }
    @Test
    @Rollback
    void persistActivityValidatingIncorrectNameActivity(){
        ActivityVO activityVO = (ActivityVO) activityService.persistActivity(activityDTO).getData();
        assertNotEquals("test activity 2", activityVO.getActivityName());
    }
    @Test
    @Rollback
    void persistActivityValidatingCorrectDescriptionActivity(){
        ActivityVO activityVO = (ActivityVO) activityService.persistActivity(activityDTO).getData();
        assertEquals("test activity description", activityVO.getActivityDescription());
    }
    @Test
    @Rollback
    void persistActivityValidatingIncorrectDescriptionActivity(){
        ActivityVO activityVO = (ActivityVO) activityService.persistActivity(activityDTO).getData();
        assertNotEquals("test activity description 2", activityVO.getActivityDescription());
    }
    @Test
    @Rollback
    void persistActivityValidatingCorrectIdProject(){
        ActivityVO activityVO = (ActivityVO) activityService.persistActivity(activityDTO).getData();
        assertEquals(1, activityVO.getProject().getId());
    }
    @Test
    @Rollback
    void persistActivityValidatingIncorrectIdProject(){
        ActivityVO activityVO = (ActivityVO) activityService.persistActivity(activityDTO).getData();
        assertNotEquals(2, activityVO.getProject().getId());
    }
    @Test
    @Rollback
    void persistActivityValidatingCorrectDateActivity(){
        ActivityVO activityVO = (ActivityVO) activityService.persistActivity(activityDTO).getData();
        assertEquals(new Date(2022, 1, 1), activityVO.getActivityDate());
    }
    @Test
    void findAllVOTestingCorrectQuantity() {
        int size = activityService.findAllVO().size();
        assertEquals(10, size);
    }

    @Test
    void findAllVOTestingIncorrectQuantity() {
        int size = activityService.findAllVO().size();
        assertNotEquals(11, size);
    }

    @Test
    void findAllVOTestingPaginationCorrectContentSize() {
        Pageable pageable = PageRequest.of(0, 5);
        int size = activityService.findAllVO(pageable).getContent().size();
        assertEquals(5, size);
    }

    @Test
    void findAllVOTestingPaginationIncorrectContentSize() {
        Pageable pageable = PageRequest.of(0, 5);
        int size = activityService.findAllVO(pageable).getContent().size();
        assertNotEquals(6, size);
    }
    @Test
    void findAllVOTestingPaginationCorrectPageNumber(){
        Pageable pageable = PageRequest.of(0, 5);
        int number = activityService.findAllVO(pageable).getNumber();
        assertEquals(0, number);
    }
    @Test
    void findAllVOTestingPaginationIncorrectPageNumber(){
        Pageable pageable = PageRequest.of(0, 5);
        int number = activityService.findAllVO(pageable).getNumber();
        assertNotEquals(1, number);
    }
    @Test
    void findAllVOTestingPaginationCorrectTotalPages(){
        Pageable pageable = PageRequest.of(0, 5);
        int totalPages = activityService.findAllVO(pageable).getTotalPages();
        assertEquals(2, totalPages);
    }
    @Test
    void findAllVOTestingPaginationIncorrectTotalPages(){
        Pageable pageable = PageRequest.of(0, 5);
        int totalPages = activityService.findAllVO(pageable).getTotalPages();
        assertNotEquals(3, totalPages);
    }
    @Test
    @Rollback
    void update() {
        activityDTO.setActivityDescription("modification");
        ActivityVO activityVO = (ActivityVO) activityService.update(activityDTO, 1).getData();
        assertEquals("modification", activityVO.getActivityDescription());
    }

    @Test
    void delete() {
        activityService.delete(1);
        ActivityVO activityVO = (ActivityVO) activityService.findById(1).getData();
        assertNull(activityVO);
    }

}