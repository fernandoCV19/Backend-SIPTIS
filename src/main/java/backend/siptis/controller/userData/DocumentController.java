package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import backend.siptis.service.document.DocumentGeneratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document")
@CrossOrigin
public class DocumentController {

    @Autowired
    private DocumentGeneratorServiceImpl documentGeneratorService;


    @GetMapping("/{id}")
    ResponseEntity<?> getDocumentsFromUser(@PathVariable("id") long userId){
        return createResponseEntity(documentGeneratorService.getAllDocumentsFromUser(userId));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteDocument(@PathVariable("id") long documentId){
        return createResponseEntity(documentGeneratorService.deleteDocument(documentId));
    }

    @PostMapping("/create-report")
    ResponseEntity<?> createReport(@RequestBody ReportDocumentDTO reportDocumentDTO){
        return createResponseEntity(documentGeneratorService.generateReport(reportDocumentDTO));
    }

    @PostMapping("/create-report-test")
    ResponseEntity<?> createReportTest(@RequestBody ReportDocumentDTO reportDocumentDTO){
        return createResponseEntity(documentGeneratorService.generateReportTesting(reportDocumentDTO));
    }

    @GetMapping("/create-solvency/{id}")
    ResponseEntity<?> createSolvency(@PathVariable("id") long userId){
        return createResponseEntity(documentGeneratorService.generateSolvency(userId));
    }

    @GetMapping("/create-solvency-test/{id}")
    ResponseEntity<?> createSolvencyTest(@PathVariable("id") long userId){
        return createResponseEntity(documentGeneratorService.generateSolvencyTesting(userId));
    }


    @PostMapping("/create-documentary-record")
    ResponseEntity<?> createDocumentaryRecord(@RequestBody DocumentaryRecordDto documentaryRecordDto){
        return createResponseEntity(documentGeneratorService.generateDocumentaryRecord(documentaryRecordDto));
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage mensajeServicio = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if(mensajeServicio == ServiceMessage.NOT_FOUND || mensajeServicio == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
