package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import backend.siptis.service.document.DocumentGeneratorServiceImpl;
import backend.siptis.service.userData.userAuthentication.UserAuthService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
@CrossOrigin
public class DocumentController {

    private final DocumentGeneratorServiceImpl documentGeneratorService;
    private final UserAuthService userAuthService;

    @GetMapping("/")
    ResponseEntity<?> getDocumentsFromUser(@RequestHeader(name = "Authorization") String token){
        Long userId = userAuthService.getIdFromToken(token);
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

    @GetMapping("/create-solvency")
    ResponseEntity<?> createSolvency(@RequestHeader(name = "Authorization") String token){
        Long userId = userAuthService.getIdFromToken(token);
        return createResponseEntity(documentGeneratorService.generateSolvency(userId));
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
