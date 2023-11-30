package backend.siptis.controller.user_data;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.document.DocumentaryRecordDto;
import backend.siptis.model.pjo.dto.document.LetterGenerationRequestDTO;
import backend.siptis.model.pjo.dto.document.ReportDocumentDTO;
import backend.siptis.service.auth.siptis_user_services.SiptisUserServiceTokenOperations;
import backend.siptis.service.user_data.document.DocumentGeneratorServiceImpl;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = ControllerConstants.Document.TAG_NAME, description = ControllerConstants.Document.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Document.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class DocumentController {

    private final DocumentGeneratorServiceImpl documentGeneratorService;
    private final SiptisUserServiceTokenOperations siptisUserServiceTokenOperations;

    @Operation(summary = "Get all documents belonging to a user")
    @GetMapping("/")
    @PreAuthorize("hasAuthority('STUDENT')")
    ResponseEntity<ControllerAnswer> getDocumentsFromUser(@RequestHeader(name = "Authorization") String token) {
        Long userId = siptisUserServiceTokenOperations.getIdFromToken(token);
        return createResponseEntity(documentGeneratorService.getAllDocumentsFromUser(userId));
    }

    @Operation(summary = "Get all documents belonging to a project's students ")
    @GetMapping("/project/{id}")
    ResponseEntity<ControllerAnswer> getDocumentsFromProject(@PathVariable("id") long id) {
        return createResponseEntity(documentGeneratorService.getAllDocumentsFromProject(id));
    }

    @Operation(summary = "Delete a document")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    ResponseEntity<ControllerAnswer> deleteDocument(@PathVariable("id") long documentId) {
        return createResponseEntity(documentGeneratorService.deleteDocument(documentId));
    }

    @Operation(summary = "Create student report document")
    @PostMapping("/create-report")
    @PreAuthorize("hasAuthority('STUDENT')")
    ResponseEntity<ControllerAnswer> createReport(@RequestHeader(name = "Authorization") String token, @RequestBody ReportDocumentDTO reportDocumentDTO) {
        Long userId = siptisUserServiceTokenOperations.getIdFromToken(token);
        List<?> projects = siptisUserServiceTokenOperations.getProjectsFromToken(token);
        int projectId = (int) projects.get(0);
        return createResponseEntity(documentGeneratorService.generateReport(reportDocumentDTO, userId, (long) projectId));
    }

    @Operation(summary = "Create student solvency document")
    @GetMapping("/create-solvency")
    @PreAuthorize("hasAuthority('STUDENT')")
    ResponseEntity<ControllerAnswer> createSolvency(@RequestHeader(name = "Authorization") String token) {
        Long userId = siptisUserServiceTokenOperations.getIdFromToken(token);
        return createResponseEntity(documentGeneratorService.generateSolvency(userId));
    }

    @Operation(summary = "Create tribunal approval letter")
    @PostMapping("/create-tribunal-approval")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> createTribunalApproval(@RequestBody LetterGenerationRequestDTO dto) {
        return createResponseEntity(documentGeneratorService.generateTribunalApproval(dto));
    }

    @Operation(summary = "Create teacher approval letter")
    @PostMapping("/create-teacher-approval-letter")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> createTeacherTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) {
        return createResponseEntity(documentGeneratorService.teacherTribunalRequest(dto));
    }

    @Operation(summary = "Create student's tribunal request letter")
    @PostMapping("/create-student-tribunal-request")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> createStudentTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) {
        return createResponseEntity(documentGeneratorService.studentTribunalRequest(dto));
    }

    @Operation(summary = "Create documentary record")
    @PostMapping("/create-documentary-record")
    @PreAuthorize("hasAuthority('STUDENT')")
    ResponseEntity<ControllerAnswer> createDocumentaryRecord(@RequestHeader(name = "Authorization") String token, @RequestBody DocumentaryRecordDto documentaryRecordDto) {
        Long userId = siptisUserServiceTokenOperations.getIdFromToken(token);
        List<?> projects = siptisUserServiceTokenOperations.getProjectsFromToken(token);
        int projectId = (int) projects.get(0);
        return createResponseEntity(documentGeneratorService.generateDocumentaryRecord(documentaryRecordDto, userId, (long) projectId));
    }

    @Operation(summary = "Create tutor approval letter")
    @PostMapping("/create-tutor-approval-letter")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> createTutorTribunalRequest(@RequestBody LetterGenerationRequestDTO dto){
        return createResponseEntity(documentGeneratorService.tutorTribunalRequest(dto));
    }

    @Operation(summary = "Create supervisor approval letter")
    @PostMapping("/create-supervisor-approval-letter")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> createSupervisorTribunalRequest(@RequestBody LetterGenerationRequestDTO dto) {
        return createResponseEntity(documentGeneratorService.supervisorTribunalRequest(dto));
    }

    private ResponseEntity<ControllerAnswer> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage message = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if (message == ServiceMessage.NOT_FOUND || message == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        if (message == ServiceMessage.CANNOT_GENERATE_LETTER ||
                message == ServiceMessage.ERROR ||
                message == ServiceMessage.NOT_APPROVED ||
                message == ServiceMessage.NO_CURRENT_DIRECTOR)
            httpStatus = HttpStatus.BAD_REQUEST;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(message.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
