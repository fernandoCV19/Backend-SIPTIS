package backend.siptis.service.userData.document;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.document.LetterGenerationRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DocumentGeneratorServiceTest {
    private DocumentGeneratorService documentGeneratorService;
    private LetterGenerationRequestDTO letterGenerationRequestDTO;

    @Autowired
    DocumentGeneratorServiceTest(DocumentGeneratorService documentGeneratorService){
        this.documentGeneratorService = documentGeneratorService;
    }

    private void createLetterGenerationRequestDTO(){
        letterGenerationRequestDTO = new LetterGenerationRequestDTO();
        letterGenerationRequestDTO.setProjectId(123l);
        letterGenerationRequestDTO.setUserId(123L);
    }
    @Test
    @DisplayName("Test get all documents from user")
    public void givenBadUserIdWhenGetAllDocumentsFromUserThenServiceMessageNOT_FOUND(){
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.getAllDocumentsFromUser(123L).getServiceMessage());
    }
    @Test
    @DisplayName("Test get all documents from project")
    public void givenBadIdProjectWhenGetAllDocumentsFromProjectThenServiceMessageNOT_FOUND(){
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.getAllDocumentsFromProject(123L).getServiceMessage());
    }
    @Test
    @DisplayName("Test delete document")
    public void givenBadIdDocumentWhenDeleteDocumentThenServiceMessageNOT_FOUND(){
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.deleteDocument(123L).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate report ")
    public void givenBadProjectIdWhenGenerateReportThenServiceMessageError(){
        assertEquals(ServiceMessage.ERROR, documentGeneratorService.generateReport(null, 123L, 123l).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate documentary record ")
    public void givenBadProjectIdWhenGenerateDocumentaryRecordThenServiceMessageError(){
        assertEquals(ServiceMessage.ERROR, documentGeneratorService.generateDocumentaryRecord(null, 123L, 123l).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate solvency ")
    public void givenBadUserIdWhenGenerateSolvencyThenServiceMessageNOT_FOUND(){
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.generateSolvency( 123l).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate teacher tribunal request ")
    public void givenLetterGenerationRequestDTOWhenGenerateTeacherTribunalRequestThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.teacherTribunalRequest( letterGenerationRequestDTO).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate tutor tribunal request ")
    public void givenLetterGenerationRequestDTOWhenGenerateTutorTribunalRequestThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.tutorTribunalRequest( letterGenerationRequestDTO).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate supervisor tribunal request ")
    public void givenLetterGenerationRequestDTOWhenGenerateSupervisorTribunalRequestThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.supervisorTribunalRequest( letterGenerationRequestDTO).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate student tribunal request ")
    public void givenLetterGenerationRequestDTOWhenGenerateStudentTribunalRequestThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.studentTribunalRequest( letterGenerationRequestDTO).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate  tribunal approval ")
    public void givenLetterGenerationRequestDTOWhenGenerateTribunalApprovalThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.generateTribunalApproval( letterGenerationRequestDTO).getServiceMessage());
    }

}
