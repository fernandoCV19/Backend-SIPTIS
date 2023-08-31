package backend.siptis.service.document;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;

import java.io.IOException;

public interface DocumentGeneratorService {
    ServiceAnswer getAllDocumentsFromUser (long idUser);

    ServiceAnswer deleteDocument (long idDocument);

    ServiceAnswer generateReport (ReportDocumentDTO reportDocumentDTO);

    ServiceAnswer generateReportTesting (ReportDocumentDTO reportDocumentDTO);

    ServiceAnswer generateSolvency(long idUser);

    ServiceAnswer generateSolvencyTesting(long idUser);

    ServiceAnswer pruebaDoc() throws IOException;

    ServiceAnswer generateDocumentaryRecord(DocumentaryRecordDto documentaryRecordDto);
}
