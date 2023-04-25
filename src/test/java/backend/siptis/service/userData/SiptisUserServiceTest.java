package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.vo.userData.TribunalInfoToAssignSection;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class SiptisUserServiceTest {

    private final SiptisUserService siptisUserService;

    @Autowired
    SiptisUserServiceTest(SiptisUserService siptisUserService) {
        this.siptisUserService = siptisUserService;
    }

    @Test
    void getPossibleTribunalsReturnMessageOk() {
        ServiceAnswer query = siptisUserService.getPossibleTribunals();
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void getPossibleTribunalsReturnAList(){
        ServiceAnswer query = siptisUserService.getPossibleTribunals();
        List<TribunalInfoToAssignSection> tribunals = (List<TribunalInfoToAssignSection>) query.getData();
        assertTrue(tribunals.size() > 0);
    }
}