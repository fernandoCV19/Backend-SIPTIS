package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.pjo.dto.document.LetterGenerationRequestDTO;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import backend.siptis.service.document.DocumentGeneratorServiceImpl;
import backend.siptis.service.userData.RefreshTokenService;
import backend.siptis.service.userData.SiptisUserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.io.*;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
@CrossOrigin
public class DocumentController {

    private final DocumentGeneratorServiceImpl documentGeneratorService;
    private final SiptisUserService userAuthService;

    @GetMapping("/")
    ResponseEntity<?> getDocumentsFromUser(@RequestHeader(name = "Authorization") String token){
        Long userId = userAuthService.getIdFromToken(token);
        return createResponseEntity(documentGeneratorService.getAllDocumentsFromUser(userId));
    }

    @GetMapping("/project/{id}")
    ResponseEntity<?> getDocumentsFromProject(@PathVariable("id") long userId){
        return createResponseEntity(documentGeneratorService.getAllDocumentsFromProject(userId));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteDocument(@PathVariable("id") long documentId){
        return createResponseEntity(documentGeneratorService.deleteDocument(documentId));
    }


    @PostMapping("/create-report")
    ResponseEntity<?> createReport(@RequestHeader(name = "Authorization") String token, @RequestBody ReportDocumentDTO reportDocumentDTO){
        Long userId = userAuthService.getIdFromToken(token);

        return createResponseEntity(documentGeneratorService.generateReport(reportDocumentDTO));
    }

    @GetMapping("/create-solvency/{id}")
    ResponseEntity<?> createSolvency(@PathVariable("id") long userId){
        return createResponseEntity(documentGeneratorService.generateSolvency(userId));
    }

    @PostMapping("/create-tribunal-approval")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> createTribunalApproval(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.generateTribunalApproval(dto));
    }

    @PostMapping("/create-teacher-approval-letter")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> createTeacherTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.teacherTribunalRequest(dto));
    }

    @PostMapping("/create-tutor-approval-letter")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> createTutorTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.tutorTribunalRequest(dto));
    }

    @PostMapping("/create-supervisor-approval-letter")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> createSupervisorTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.supervisorTribunalRequest(dto));
    }

    @PostMapping("/create-student-tribunal-request")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> createStudentTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.studentTribunalRequest(dto));
    }


    @GetMapping("/create-solvency")
    ResponseEntity<?> createSolvency(@RequestHeader(name = "Authorization") String token) {
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

        if(mensajeServicio == ServiceMessage.CANNOT_GENERATE_LETTER || mensajeServicio == ServiceMessage.ERROR)
            httpStatus = HttpStatus.BAD_REQUEST;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
