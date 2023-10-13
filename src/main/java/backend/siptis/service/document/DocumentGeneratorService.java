package backend.siptis.service.document;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;

import java.io.IOException;

public interface DocumentGeneratorService {
    ServiceAnswer getAllDocumentsFromUser(long idUser);

    ServiceAnswer deleteDocument(long idDocument);

    ServiceAnswer generateReport(ReportDocumentDTO reportDocumentDTO, Long idUser, Long idProject);

    ServiceAnswer generateDocumentaryRecord(DocumentaryRecordDto documentaryRecordDto, Long idUser, Long idProject);

    ServiceAnswer generateSolvency(long idUser);

    ServiceAnswer tribunalRequest(long id) throws IOException;

    ServiceAnswer generateTribunalApproval(Long id) throws IOException;

}
