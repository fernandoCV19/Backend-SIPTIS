package backend.siptis.service.document;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.pjo.dto.document.LetterGenerationRequestDTO;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

public interface DocumentGeneratorService {
    ServiceAnswer getAllDocumentsFromUser (long idUser);

    ServiceAnswer deleteDocument (long idDocument);

    ServiceAnswer generateReport (ReportDocumentDTO reportDocumentDTO);

    ServiceAnswer generateSolvency(long idUser);

    ServiceAnswer teacherTribunalRequest(LetterGenerationRequestDTO dto) throws IOException;

    ServiceAnswer tutorTribunalRequest(LetterGenerationRequestDTO dto) throws IOException;

    ServiceAnswer supervisorTribunalRequest(LetterGenerationRequestDTO dto) throws IOException;

    ServiceAnswer studentTribunalRequest(LetterGenerationRequestDTO dto) throws IOException;

    ServiceAnswer generateTribunalApproval(LetterGenerationRequestDTO dto) throws IOException;

    ServiceAnswer generateDocumentaryRecord(DocumentaryRecordDto documentaryRecordDto);
}
