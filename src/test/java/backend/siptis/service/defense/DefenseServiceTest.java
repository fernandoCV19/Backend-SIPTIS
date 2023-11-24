package backend.siptis.service.defense;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.project_management.DefenseDTO;
import backend.siptis.model.repository.defense_management.DefenseRepository;
import backend.siptis.service.defense_management.DefenseService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DefenseServiceTest {

    private final DefenseService defenseService;
    private final DefenseRepository defenseRepository;
    private static final DefenseDTO DEFENSE_DTO = new DefenseDTO();

    @Autowired
    DefenseServiceTest(DefenseService defenseService, DefenseRepository defenseRepository) {
        this.defenseService = defenseService;
        this.defenseRepository = defenseRepository;
    }

    @BeforeEach
    public void setUp() {
        DEFENSE_DTO.setDate(LocalDate.of(2023, 12, 11));
        DEFENSE_DTO.setProjectId(100L);
        DEFENSE_DTO.setStartTime(LocalTime.of(11, 11));
        DEFENSE_DTO.setEndTime(LocalTime.of(12, 12));
        DEFENSE_DTO.setSubstituteName("DEFENSE SUBSTITUTE NAME");
        DEFENSE_DTO.setPlaceId(101L);
    }


    @Test
    @Rollback
    @DisplayName(("Test for get all defenses"))
    void givenAMonthWhenGetDefenseByMonthThenSericeMessageHasData() {
        ServiceAnswer sa = defenseService.getDefenseByMonth(11);
        // TODO: lazy exception
        assertNotNull(defenseService.getDefenseByMonth(1).getData());
    }

    @Test
    @Rollback
    @DisplayName("Test for create a new defense")
    void givenANewDefenseWhenRegisterDefenseThenServiceMessageOK() {
        ServiceAnswer sa = defenseService.registerDefense(DEFENSE_DTO);
        Assertions.assertEquals(ServiceMessage.OK, sa.getServiceMessage());
    }

    @Test
    @Rollback
    @DisplayName("Test for create a new defense with null values")
    void givenANewDefenseWithNullValuesWhenRegisterDefenseThenServiceMessageINVALIDDATE() {
        DEFENSE_DTO.setDate(null);
        DEFENSE_DTO.setProjectId(null);
        DEFENSE_DTO.setStartTime(null);
        DEFENSE_DTO.setEndTime(null);
        DEFENSE_DTO.setSubstituteName(null);
        DEFENSE_DTO.setPlaceId(null);
        ServiceAnswer sa = defenseService.registerDefense(DEFENSE_DTO);
        Assertions.assertEquals(ServiceMessage.INVALID_DATE, sa.getServiceMessage());
    }

    @Test
    @Rollback
    @DisplayName("Test for create a new defense with invalid values")
    void givenANewDefenseWithInvalidValuesWhenRegisterDefenseThenServiceDataIsNotNull() {
        ServiceAnswer sa = defenseService.registerDefense(DEFENSE_DTO);
        Assertions.assertNotNull(sa.getData());
    }

    @Test
    @Rollback
    @DisplayName("Test for remove a defense")

    void givenADefenseIdWhenRemoveDefenseThenServiceMessageOK() {
        ServiceAnswer sa = defenseService.removeDefense(101L);
        Assertions.assertEquals(ServiceMessage.OK, sa.getServiceMessage());
    }

    @Test
    @Rollback
    @DisplayName("Test for remove a defense with invalid id")

    void givenAProjectIdWhenRemoveDefenseThenServiceMessagePROJECTIDDOESNOTEXIST() {
        ServiceAnswer sa = defenseService.removeDefense(1L);
        Assertions.assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, sa.getServiceMessage());
    }

    @Test
    @Rollback
    @DisplayName("Test for remove a defense verify correct remove")

    void givenADefenseIdWhenRemoveDefenseThenDefenseIsNotPresent() {
        defenseService.removeDefense(101L);
        Assertions.assertFalse(defenseRepository.findById(101L).isPresent());
    }
}