package backend.siptis.service.user_data.document;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.user_data.Document;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.pjo.dto.document.LetterGenerationRequestDTO;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import backend.siptis.model.repository.user_data.DocumentRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DocumentGeneratorServiceTest {
    @Autowired
    private DocumentGeneratorService documentGeneratorService;
    @Autowired
    private SiptisUserRepository siptisUserRepository;
    @Autowired
    private DocumentRepository documentRepository;
    private LetterGenerationRequestDTO letterGenerationRequestDTO;
    private Document document;
    private ReportDocumentDTO reportDocumentDTO;
    private DocumentaryRecordDto documentaryRecordDto;


    private void createLetterGenerationRequestDTO() {
        letterGenerationRequestDTO = new LetterGenerationRequestDTO();
        letterGenerationRequestDTO.setProjectId(100l);
        letterGenerationRequestDTO.setUserId(123L);
    }

    private void createReportDocumentDTO(){
        reportDocumentDTO = new ReportDocumentDTO();
        reportDocumentDTO.setReportNumber(123);
        reportDocumentDTO.setDescription("description");
        reportDocumentDTO.setShortDescription("short");
    }
    private void createDocumentaryRecordDTO(){
        documentaryRecordDto = new DocumentaryRecordDto();
        documentaryRecordDto.setConsultants("cons");
        documentaryRecordDto.setDefenseDate("date");
        documentaryRecordDto.setDegree(true);
        documentaryRecordDto.setMention("mention");
        documentaryRecordDto.setKeyWords("key");
        documentaryRecordDto.setSummary("sumary");
        documentaryRecordDto.setPageQuantity("12");
    }

    private void createDocument(){
        SiptisUser user = siptisUserRepository.findById(100L).get();
        document = new Document();
        document.setDate(LocalDateTime.now());
        document.setDescription("description");
        document.setPath("path");
        document.setSiptisUser(user);
        documentRepository.save(document);
    }


    @Test
    @DisplayName("Test get all documents from user")
    void givenBadUserId_WhenGetAllDocumentsFromUser_ThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.getAllDocumentsFromUser(123L).getServiceMessage());
    }
    @Test
    @DisplayName("Test get all documents from user no documents")
    @Sql(scripts = {"/testDB.sql", "/custom_imports/create_documents.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadUserId_WhenGetAllDocumentsFromUser_ThenServiceMessageOK() {
        createDocument();
        assertEquals(ServiceMessage.OK, documentGeneratorService.getAllDocumentsFromUser(100L).getServiceMessage());
    }

    @Test
    @DisplayName("Test get all documents from project")
    void givenBadIdProject_WhenGetAllDocumentsFromProject_ThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.getAllDocumentsFromProject(123L).getServiceMessage());
    }
    @Test
    @DisplayName("Test get all documents from project success")
    @Sql(scripts = {"/testDB.sql", "/custom_imports/create_documents.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadIdProject_WhenGetAllDocumentsFromProject_ThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, documentGeneratorService.getAllDocumentsFromProject(112L).getServiceMessage());
    }

    @Test
    @DisplayName("Test delete document")
    void givenBadIdDocument_WhenDeleteDocument_ThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.deleteDocument(123L).getServiceMessage());
    }
    @Test
    @Sql(scripts = {"/testDB.sql", "/custom_imports/create_documents.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadIdDocument_WhenDeleteDocument_ThenServiceMessageDOCUMENT_DELETED() {
        assertEquals(ServiceMessage.DOCUMENT_DELETED, documentGeneratorService.deleteDocument(50l).getServiceMessage());
    }

    @Test
    @DisplayName("Test generate report error ")
    void givenBadProjectId_WhenGenerateReport_ThenServiceMessageError() {
        assertEquals(ServiceMessage.ERROR, documentGeneratorService.generateReport(null, 123L, 123L).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate report document generated")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadProjectId_WhenGenerateReport_ThenServiceMessageDOCUMENT_GENERATED() {
        createReportDocumentDTO();
        assertEquals(ServiceMessage.DOCUMENT_GENERATED, documentGeneratorService.generateReport(reportDocumentDTO, 100l, 112L).getServiceMessage());
    }

    @Test
    @DisplayName("Test generate documentary record ")
    void givenBadProjectId_WhenGenerateDocumentaryRecord_ThenServiceMessageError() {
        assertEquals(ServiceMessage.ERROR, documentGeneratorService.generateDocumentaryRecord(null, 123L, 123L).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate documentary record document generated ")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadProjectId_WhenGenerateDocumentaryRecord_ThenServiceMessageDOCUMENT_GENERATED() {
        createDocumentaryRecordDTO();
        assertEquals(ServiceMessage.DOCUMENT_GENERATED, documentGeneratorService.generateDocumentaryRecord(documentaryRecordDto, 100l, 112l).getServiceMessage());
    }

    @Test
    @DisplayName("Test generate solvency ")
    void givenBadUserId_WhenGenerateSolvency_ThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.generateSolvency(123L).getServiceMessage());
    }
    @Test
    @DisplayName("Test generate solvency success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadUserId_WhenGenerateSolvency_ThenServiceMessageDOCUMENT_GENERATED() {
        assertEquals(ServiceMessage.DOCUMENT_GENERATED, documentGeneratorService.generateSolvency(100L).getServiceMessage());
    }

    /*@Test
    @DisplayName("Test generate teacher tribunal request success ")
    void givenLetterGenerationRequestDTO_WhenGenerateTeacherTribunalRequest_ThenServiceMessageDOCUMENT_GENERATED() throws IOException {
        createLetterGenerationRequestDTO();
        letterGenerationRequestDTO.setUserId(120l);
        letterGenerationRequestDTO.setProjectId(101l);
        assertEquals(ServiceMessage.DOCUMENT_GENERATED, documentGeneratorService.teacherTribunalRequest(letterGenerationRequestDTO).getServiceMessage());
    }*/
    @Test
    @DisplayName("Test generate teacher tribunal request ")
    void givenLetterGenerationRequestDTO_WhenGenerateTeacherTribunalRequest_ThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.teacherTribunalRequest(letterGenerationRequestDTO).getServiceMessage());
    }

    @Test
    @DisplayName("Test generate tutor tribunal request ")
    void givenLetterGenerationRequestDTO_WhenGenerateTutorTribunalRequest_ThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.tutorTribunalRequest(letterGenerationRequestDTO).getServiceMessage());
    }

    @Test
    @DisplayName("Test generate supervisor tribunal request ")
    void givenLetterGenerationRequestDTO_WhenGenerateSupervisorTribunalRequest_ThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.supervisorTribunalRequest(letterGenerationRequestDTO).getServiceMessage());
    }

    @Test
    @DisplayName("Test generate student tribunal request ")
    void givenLetterGenerationRequestDTO_WhenGenerateStudentTribunalRequest_ThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.studentTribunalRequest(letterGenerationRequestDTO).getServiceMessage());
    }

    @Test
    @DisplayName("Test generate  tribunal approval ")
    void givenLetterGenerationRequestDTO_WhenGenerateTribunalApproval_ThenServiceMessageNOT_FOUND() throws IOException {
        createLetterGenerationRequestDTO();
        assertEquals(ServiceMessage.NOT_FOUND, documentGeneratorService.generateTribunalApproval(letterGenerationRequestDTO).getServiceMessage());
    }

}
