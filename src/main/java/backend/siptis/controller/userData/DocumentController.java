package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.pjo.dto.document.LetterGenerationRequestDTO;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import backend.siptis.service.auth.siptisUserServices.SiptisUserServiceTokenOperations;
import backend.siptis.service.userData.document.DocumentGeneratorServiceImpl;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(ControllerConstants.Document.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class DocumentController {

    private final DocumentGeneratorServiceImpl documentGeneratorService;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;


    @GetMapping("/")
    ResponseEntity<ControllerAnswer> getDocumentsFromUser(@RequestHeader(name = "Authorization") String token) {
        Long userId = siptisUserServiceTokenOperations.getIdFromToken(token);
        return createResponseEntity(documentGeneratorService.getAllDocumentsFromUser(userId));
    }

    @GetMapping("/project/{id}")
    ResponseEntity<ControllerAnswer> getDocumentsFromProject(@PathVariable("id") long userId) {
        return createResponseEntity(documentGeneratorService.getAllDocumentsFromProject(userId));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ControllerAnswer> deleteDocument(@PathVariable("id") long documentId) {
        return createResponseEntity(documentGeneratorService.deleteDocument(documentId));
    }


    @PostMapping("/create-report")
    ResponseEntity<ControllerAnswer> createReport(@RequestHeader(name = "Authorization") String token, @RequestBody ReportDocumentDTO reportDocumentDTO) {
        Long userId = siptisUserServiceTokenOperations.getIdFromToken(token);
        List<?> projects = siptisUserServiceTokenOperations.getProjectsFromToken(token);
        int projectId = (int) projects.get(0);
        return createResponseEntity(documentGeneratorService.generateReport(reportDocumentDTO, userId, (long) projectId));
    }

    @GetMapping("/create-solvency")
    ResponseEntity<ControllerAnswer> createSolvency(@RequestHeader(name = "Authorization") String token) {
        Long userId = siptisUserServiceTokenOperations.getIdFromToken(token);
        return createResponseEntity(documentGeneratorService.generateSolvency(userId));
    }

    @PostMapping("/create-tribunal-approval")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> createTribunalApproval(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.generateTribunalApproval(dto));
    }

    @PostMapping("/create-teacher-approval-letter")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> createTeacherTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.teacherTribunalRequest(dto));
    }

    @PostMapping("/create-student-tribunal-request")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> createStudentTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.studentTribunalRequest(dto));
    }

    @PostMapping("/create-documentary-record")
    ResponseEntity<ControllerAnswer> createDocumentaryRecord(@RequestHeader(name = "Authorization") String token, @RequestBody DocumentaryRecordDto documentaryRecordDto) {
        Long userId = siptisUserServiceTokenOperations.getIdFromToken(token);
        List<?> projects = siptisUserServiceTokenOperations.getProjectsFromToken(token);
        int projectId = (int) projects.get(0);
        return createResponseEntity(documentGeneratorService.generateDocumentaryRecord(documentaryRecordDto, userId, (long) projectId));
    }

    @PostMapping("/create-tutor-approval-letter")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> createTutorTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.tutorTribunalRequest(dto));
    }

    @PostMapping("/create-supervisor-approval-letter")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> createSupervisorTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) throws IOException {
        return createResponseEntity(documentGeneratorService.supervisorTribunalRequest(dto));
    }

    private ResponseEntity<ControllerAnswer> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage mensajeServicio = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if (mensajeServicio == ServiceMessage.NOT_FOUND || mensajeServicio == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        if (mensajeServicio == ServiceMessage.CANNOT_GENERATE_LETTER || mensajeServicio == ServiceMessage.ERROR || mensajeServicio == ServiceMessage.NOT_APPROVED)
            httpStatus = HttpStatus.BAD_REQUEST;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(mensajeServicio.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
