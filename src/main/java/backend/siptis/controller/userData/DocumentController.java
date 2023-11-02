package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.pjo.dto.document.LetterGenerationRequestDTO;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import backend.siptis.service.document.DocumentGeneratorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.*;

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

    @GetMapping("/create-solvency/{id}")
    ResponseEntity<?> createSolvency(@PathVariable("id") long userId){
        return createResponseEntity(documentGeneratorService.generateSolvency(userId));
    }

    @PostMapping("/create-tribunal-approval")
    ResponseEntity<?> createTribunalApproval(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.generateTribunalApproval(dto));
    }

    @PostMapping("/create-teacher-approval-letter")
    ResponseEntity<?> createTeacherTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.teacherTribunalRequest(dto));
    }

    @PostMapping("/create-tutor-approval-letter")
    ResponseEntity<?> createTutorTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.tutorTribunalRequest(dto));
    }

    @PostMapping("/create-student-tribunal-request")
    ResponseEntity<?> createStudentTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.studentTribunalRequest(dto));
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

        if(mensajeServicio == ServiceMessage.CANNOT_GENERATE_LETTER || mensajeServicio == ServiceMessage.ERROR)
            httpStatus = HttpStatus.BAD_REQUEST;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
