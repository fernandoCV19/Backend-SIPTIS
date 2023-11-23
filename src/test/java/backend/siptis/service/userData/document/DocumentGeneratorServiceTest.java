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
class DocumentGeneratorServiceTest {
    @Autowired
    private DocumentGeneratorService documentGeneratorService;
    private LetterGenerationRequestDTO letterGenerationRequestDTO;

    private void createLetterGenerationRequestDTO(){
        letterGenerationRequestDTO = new LetterGenerationRequestDTO();
        letterGenerationRequestDTO.setProjectId(123l);
        letterGenerationRequestDTO.setUserId(123L);
    }
    @Test
    @DisplayName("Test get all documents from user")
    void givenBadUserId_WhenGetAllDocumentsFromUser_ThenServiceMessageNOT_FOUND(){
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.getAllDocumentsFromUser(123L).getServiceMessage());
    }
    @Test
    @DisplayName("Test get all documents from project")
    void givenBadIdProject_WhenGetAllDocumentsFromProject_ThenServiceMessageNOT_FOUND(){
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.getAllDocumentsFromProject(123L).getServiceMessage());
    }
    @Test
    @DisplayName("Test delete document")
    void givenBadIdDocument_WhenDeleteDocument_ThenServiceMessageNOT_FOUND(){
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.deleteDocument(123L).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate report ")
    void givenBadProjectId_WhenGenerateReport_ThenServiceMessageError(){
        assertEquals(ServiceMessage.ERROR, documentGeneratorService.generateReport(null, 123L, 123l).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate documentary record ")
    void givenBadProjectId_WhenGenerateDocumentaryRecord_ThenServiceMessageError(){
        assertEquals(ServiceMessage.ERROR, documentGeneratorService.generateDocumentaryRecord(null, 123L, 123l).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate solvency ")
    void givenBadUserId_WhenGenerateSolvency_ThenServiceMessageNOT_FOUND(){
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.generateSolvency( 123l).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate teacher tribunal request ")
    void givenLetterGenerationRequestDTO_WhenGenerateTeacherTribunalRequest_ThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.teacherTribunalRequest( letterGenerationRequestDTO).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate tutor tribunal request ")
    void givenLetterGenerationRequestDTO_WhenGenerateTutorTribunalRequest_ThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.tutorTribunalRequest( letterGenerationRequestDTO).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate supervisor tribunal request ")
    void givenLetterGenerationRequestDTO_WhenGenerateSupervisorTribunalRequest_ThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.supervisorTribunalRequest( letterGenerationRequestDTO).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate student tribunal request ")
    void givenLetterGenerationRequestDTO_WhenGenerateStudentTribunalRequest_ThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.studentTribunalRequest( letterGenerationRequestDTO).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate  tribunal approval ")
    void givenLetterGenerationRequestDTO_WhenGenerateTribunalApproval_ThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.generateTribunalApproval( letterGenerationRequestDTO).getServiceMessage());
    }

}
