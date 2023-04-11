package backend.siptis.service.records;

import backend.siptis.model.entity.records.Activity;
import backend.siptis.model.entity.records.GeneralActivity;
import backend.siptis.model.pjo.dto.records.ActivityDTO;
import backend.siptis.model.pjo.dto.records.GeneralActivityDTO;
import backend.siptis.model.pjo.vo.GeneralActivityVO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class EmailServiceTest {
    private final EmailService emailService;
    private final GeneralActivityService generalActivityService;
    private final ActivityService activityService;
    @Autowired
    EmailServiceTest(EmailService emailService, GeneralActivityService generalActivityService, ActivityService activityService) {
        this.emailService = emailService;
        this.generalActivityService = generalActivityService;
        this.activityService = activityService;
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sendPersonalActivities() {
        GeneralActivityDTO activityDTO = new GeneralActivityDTO();
        activityDTO.setActivityDate(new Date(2022, 1, 1));
        activityDTO.setGeneralActivityName("test activity");
        activityDTO.setActivityDescription("test activity description");

        GeneralActivityVO save = (GeneralActivityVO) generalActivityService.persistGeneralActivity(activityDTO).getData();

        long a = 1;
        GeneralActivity generalActivity = (GeneralActivity) generalActivityService.findById(a).getData();
        System.out.println(generalActivity);

    }

    @Test
    void sendGeneralActivities() {
    }

    @Test
    void readFile() {
    }

    @Test
    void sendEmailFromTemplate() {
    }

    @Test
    void sendSpecificEmail() {
    }
}
