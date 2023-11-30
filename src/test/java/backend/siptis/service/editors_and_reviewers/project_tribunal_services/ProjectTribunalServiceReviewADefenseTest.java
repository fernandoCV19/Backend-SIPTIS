package backend.siptis.service.editors_and_reviewers.project_tribunal_services;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.editors_and_reviewers.ReviewADefenseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ProjectTribunalServiceReviewADefenseTest {

    private final ProjectTribunalServiceReviewADefense projectTribunalService;
    private ReviewADefenseDTO REVIEW_A_DEFENSE_DTO;

    @Autowired
    public ProjectTribunalServiceReviewADefenseTest(ProjectTribunalServiceReviewADefense projectTribunalService) {
        this.projectTribunalService = projectTribunalService;
    }

    @BeforeEach
    void setUp() {
        REVIEW_A_DEFENSE_DTO = new ReviewADefenseDTO(117L, 156L, 100.0);
    }

    @Test
    @Rollback
    void givenAnReviewADefenseDTOWhenReviewADefenseThenServiceMessageHasNotStarted() {
        assertEquals(ServiceMessage.DEFENSE_HAS_NOT_STARTED, projectTribunalService.reviewADefense(REVIEW_A_DEFENSE_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenReviewADefenseDTOWithInvalidProjectIdWhenReviewADefenseThenServiceMessageProjectIdDoesNotExist() {
        REVIEW_A_DEFENSE_DTO.setProject(999L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, projectTribunalService.reviewADefense(REVIEW_A_DEFENSE_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenReviewADefenseDTOWithInvalidTribunalIdWhenReviewADefenseThenServiceMessageUserIdDoesNotExist() {
        REVIEW_A_DEFENSE_DTO.setTribunal(999L);
        assertEquals(ServiceMessage.USER_ID_DOES_NOT_EXIST, projectTribunalService.reviewADefense(REVIEW_A_DEFENSE_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenReviewDefenseDTOWithNotMathTribunalWhenReviewADefenseThenServiceMessageIdNotMatch() {
        REVIEW_A_DEFENSE_DTO.setTribunal(157L);
        assertEquals(ServiceMessage.ID_TRIBUNAL_DOES_NOT_MATCH_WITH_PROJECT, projectTribunalService.reviewADefense(REVIEW_A_DEFENSE_DTO).getServiceMessage());
    }

    @Test
    @Rollback
    void givenReviewDefenseDTOWithInvalidScoreWhenReviewADefenseThenServiceMessageScoreIsNotValid() {
        REVIEW_A_DEFENSE_DTO.setPoints(101.0);
        assertEquals(ServiceMessage.SCORE_IS_NOT_VALID, projectTribunalService.reviewADefense(REVIEW_A_DEFENSE_DTO).getServiceMessage());
    }
}