package backend.siptis.service.editors_and_reviewers.project_tribunal_services;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.project_management.AssignTribunalsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProjectTribunalServiceModifyProjectTribunalsTest {

    private static final AssignTribunalsDTO ASSIGN_TRIBUNAL_DTO = new AssignTribunalsDTO();
    private final ProjectTribunalServiceModifyProjectTribunals projectTribunalServiceModifyProjectTribunals;


    @Autowired
    public ProjectTribunalServiceModifyProjectTribunalsTest(ProjectTribunalServiceModifyProjectTribunals projectTribunalServiceModifyProjectTribunals) {
        this.projectTribunalServiceModifyProjectTribunals = projectTribunalServiceModifyProjectTribunals;
    }


    @BeforeEach
    void setUp() {
        List<Long> tribunalsIds = new ArrayList<>();
        tribunalsIds.add(157L);
        tribunalsIds.add(158L);
        tribunalsIds.add(159L);
        ASSIGN_TRIBUNAL_DTO.setProjectId(119L);
        ASSIGN_TRIBUNAL_DTO.setTribunalsIds(tribunalsIds);
    }

    @Test
    @Rollback
    void givenValidProjectIdWhenRemoveTribunalsThenServiceMessageOk() {
        assertEquals(ServiceMessage.OK, projectTribunalServiceModifyProjectTribunals.removeTribunals(117L).getServiceMessage());
    }

    @Test
    @Rollback
    void givenInvalidProjectIdWhenRemoveTribunalsThenServiceMessageProjectIdDoesNotExist() {
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, projectTribunalServiceModifyProjectTribunals.removeTribunals(1L).getServiceMessage());
    }

    @Test
    @Rollback
    void givenAnAssignTribunalDTOWhenAssignTribunalsThenServiceMessageOk() {
        System.out.println(ASSIGN_TRIBUNAL_DTO.getTribunalsIds().toString());
        assertEquals(ServiceMessage.OK, projectTribunalServiceModifyProjectTribunals.assignTribunals(ASSIGN_TRIBUNAL_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenAnAssignTribinalDTOWithNoTribunalIdWhenAssignTribunalsThenServiceMessageIdNotExist() {
        List<Long> tribunalsIds = new ArrayList<>();
        tribunalsIds.add(1L);
        tribunalsIds.add(1L);
        tribunalsIds.add(1L);
        ASSIGN_TRIBUNAL_DTO.setTribunalsIds(tribunalsIds);
        ServiceMessage message = projectTribunalServiceModifyProjectTribunals.assignTribunals(ASSIGN_TRIBUNAL_DTO).getServiceMessage();
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, projectTribunalServiceModifyProjectTribunals.assignTribunals(ASSIGN_TRIBUNAL_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenAssignTribnalDTOWIthNoTribunalIdWhenAssignTribunalsThenServiceMessageUserIsNotTribunal() {
        List<Long> tribunalsIds = new ArrayList<>();
        tribunalsIds.add(100L);
        tribunalsIds.add(100L);
        tribunalsIds.add(100L);
        ASSIGN_TRIBUNAL_DTO.setTribunalsIds(tribunalsIds);

        ServiceMessage message = projectTribunalServiceModifyProjectTribunals.assignTribunals(ASSIGN_TRIBUNAL_DTO).getServiceMessage();
        assertEquals(ServiceMessage.USER_IS_NOT_A_TRIBUNAL, projectTribunalServiceModifyProjectTribunals.assignTribunals(ASSIGN_TRIBUNAL_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenAssignTribunalDTOWithInvalidProjectIdWhenAssignTribunalThenServiceMessageProjectIdDoesNotExist() {
        ASSIGN_TRIBUNAL_DTO.setProjectId(1L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, projectTribunalServiceModifyProjectTribunals.assignTribunals(ASSIGN_TRIBUNAL_DTO).getServiceMessage());
    }

}