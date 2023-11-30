package backend.siptis.controller.editors_and_reviewers.project_tribunal_controllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.project_management.AssignTribunalsDTO;
import backend.siptis.service.editors_and_reviewers.project_tribunal_services.ProjectTribunalServiceModifyProjectTribunals;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.ProjecTribunal.TAG_NAME, description = ControllerConstants.ProjecTribunal.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.ProjecTribunal.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class ModifyProjectTribunalsController {

    private final ProjectTribunalServiceModifyProjectTribunals projectTribunalServiceModifyProjectTribunals;

    @Operation(summary = "Remove tribunals from a project with project id")
    @DeleteMapping("/removeTribunals/{id}")
    @PreAuthorize("hasAnyAuthority('TRIBUNAL')")
    public ResponseEntity<ControllerAnswer> removeTribunalsFromAProject(@PathVariable("id") Long projectId) {
        ServiceAnswer serviceAnswer = projectTribunalServiceModifyProjectTribunals.removeTribunals(projectId);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Assign tribunals to a project with project id")
    @PostMapping("/assignTribunals")
    @PreAuthorize("hasAnyAuthority('TRIBUNAL')")
    public ResponseEntity<ControllerAnswer> assignTribunal(@RequestBody AssignTribunalsDTO assignTribunalsDTO) {
        ServiceAnswer serviceAnswer = projectTribunalServiceModifyProjectTribunals.assignTribunals(assignTribunalsDTO);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
