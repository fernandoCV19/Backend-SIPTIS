package backend.siptis.service.projectManagement;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.vo.projectManagement.InfoToReviewAProjectVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
class PresentationServiceTest {

    private final PresentationService presentationService;

    @Autowired
    PresentationServiceTest(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @Test
    void getLastReviewsFromAPresentationWithIncorrectIdReturnIdNotFound() {
        ServiceAnswer query = presentationService.getReviewsFromLastPresentation(0L);
        assertEquals(ServiceMessage.PROJECT_ID_DOES_NOT_EXIST, query.getServiceMessage());
    }

    @Test
    void getLastReviewsFromAPresentationWithIncorrectIdReturnNullData() {
        ServiceAnswer query = presentationService.getReviewsFromLastPresentation(0L);
        assertNull(query.getData());
    }

    @Test
    void getLastReviewsFromAPresentationWithProjectThatDoesNotHavePresentationsReturnThereIsNoPresentationYet() {
        ServiceAnswer query = presentationService.getReviewsFromLastPresentation(40L);
        assertEquals(ServiceMessage.THERE_IS_NO_PRESENTATION_YET, query.getServiceMessage());
    }

    @Test
    void getLastReviewsFromAPresentationWithProjectThatDoesNotHavePresetationsReturnNullData() {
        ServiceAnswer query = presentationService.getReviewsFromLastPresentation(40L);
        assertNull(query.getData());
    }

    @Test
    void getLastReviewsFromAPresentationWithProjectThatDoesNotHaveReviewReturnOk() {
        ServiceAnswer query = presentationService.getReviewsFromLastPresentation(31L);
        assertEquals(ServiceMessage.OK, query.getServiceMessage());
    }

    @Test
    void getLastReviewsFromAPresentationWithProjectThatDoesNotHaveReviewReturnEmptyList() {
        ServiceAnswer query = presentationService.getReviewsFromLastPresentation(31L);
        InfoToReviewAProjectVO listOfReviews = (InfoToReviewAProjectVO) query.getData();
        assertTrue(listOfReviews.getOtherReviews().isEmpty());
    }

    @Test
    void getLastReviewsFromAPresentationReturnAllLastReviewsFromAProject() {
        ServiceAnswer query = presentationService.getReviewsFromLastPresentation(33L);
        InfoToReviewAProjectVO listOfReviews = (InfoToReviewAProjectVO) query.getData();
        assertEquals(2, listOfReviews.getOtherReviews().size());
    }

    @Test
    void getLastReviewsFromAPresentationReturnAllCorrectLastReviewsFromAProject() {
        ServiceAnswer query = presentationService.getReviewsFromLastPresentation(33L);
        InfoToReviewAProjectVO listOfReviews = (InfoToReviewAProjectVO) query.getData();
        assertTrue(listOfReviews.getOtherReviews().get(0).getDocumentPath().equals("documentPath4") && listOfReviews.getOtherReviews().get(1).getDocumentPath().equals("documentPath5"));
    }
}