package backend.siptis.controller.document;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.Phase;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import backend.siptis.service.documents.DocumentGeneratorServiceImpl;
import backend.siptis.service.projectManagement.CloudManagementService;
import backend.siptis.service.projectManagement.PresentationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document")
@CrossOrigin
public class DocumentController {

    @Autowired
    private DocumentGeneratorServiceImpl documentGeneratorService;


    @PostMapping("/create-report")
    void createReport(@RequestBody ReportDocumentDTO reportDocumentDTO){
    }

}
