package backend.siptis.controller.user_data;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.document.LetterGenerationRequestDTO;
import backend.siptis.service.user_data.document.DocumentValidationService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.Document.TAG_NAME, description = ControllerConstants.Document.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Document.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class DocumentValidationController {
    private final DocumentValidationService documentValidationService;

    @Operation(summary = "Verify if allowed to generate student letter")
    @GetMapping("/check-student-letter")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> checkStudentTribunalRequest(@Valid LetterGenerationRequestDTO dto) {
        return createResponseEntity(documentValidationService.studentLetterValidation(dto));
    }

    @Operation(summary = "Verify if allowed to generate tutor letter")
    @GetMapping("/check-tutor-approval-letter")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> checkTutorTribunalRequest(@Valid LetterGenerationRequestDTO dto) {
        return createResponseEntity(documentValidationService.tutorLetterValidation(dto));
    }

    @Operation(summary = "Verify if allowed to generate supervisor letter")
    @GetMapping("/check-supervisor-approval-letter")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> checkSupervisorTribunalRequest(@Valid LetterGenerationRequestDTO dto) {
        return createResponseEntity(documentValidationService.supervisorLetterValidation(dto));
    }

    @Operation(summary = "Verify if allowed to generate teacher letter")
    @GetMapping("/check-teacher-approval-letter")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> checkTeacherTribunalRequest( @Valid LetterGenerationRequestDTO dto) {
        return createResponseEntity(documentValidationService.teacherLetterValidation(dto));
    }

    @Operation(summary = "Verify if allowed to generate tribunal letter")
    @GetMapping("/check-tribunal-approval-letter")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> checkTribunalApproval(@Valid LetterGenerationRequestDTO dto) {
        return createResponseEntity(documentValidationService.tribunalLetterValidation(dto));
    }

    @Operation(summary = "Verify if allowed to generate defense act")
    @GetMapping("/check-defense-act")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INF_DIRECTOR', 'SIS_DIRECTOR')")
    ResponseEntity<ControllerAnswer> checkDefenseAct(@Valid Long projectId) {
        return createResponseEntity(documentValidationService.defenseActValidation(projectId));
    }


    private ResponseEntity<ControllerAnswer> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage message = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(message.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
