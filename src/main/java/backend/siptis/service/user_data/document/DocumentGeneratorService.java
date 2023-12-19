package backend.siptis.service.user_data.document;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.document.ActRequestDTO;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.pjo.dto.document.LetterGenerationRequestDTO;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentGeneratorService {
    ServiceAnswer getAllDocumentsFromUser(long idUser);

    ServiceAnswer getAllDocumentsFromProject(long idProject);

    ServiceAnswer deleteDocument(long idDocument);

    ServiceAnswer uploadSheet(Long projectId, MultipartFile sheet);

    ServiceAnswer generateReport(ReportDocumentDTO reportDocumentDTO, Long idUser, Long idProject);

    ServiceAnswer generateDocumentaryRecord(DocumentaryRecordDto documentaryRecordDto, Long idUser, Long idProject);

    ServiceAnswer generateSolvency(long idUser);

    ServiceAnswer teacherTribunalRequest(LetterGenerationRequestDTO dto) throws IOException;

    ServiceAnswer tutorTribunalRequest(LetterGenerationRequestDTO dto) throws IOException;

    ServiceAnswer supervisorTribunalRequest(LetterGenerationRequestDTO dto) throws IOException;

    ServiceAnswer studentTribunalRequest(LetterGenerationRequestDTO dto) throws IOException;

    ServiceAnswer generateTribunalApproval(LetterGenerationRequestDTO dto) throws IOException;

    ServiceAnswer generateDefenseAct(ActRequestDTO dto);

}
