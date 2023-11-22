package backend.siptis.controller.editorsAndReviewers.ProjectTribunalControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.projectManagement.AssignTribunalsDTO;
import backend.siptis.service.editorsAndReviewers.projectTribunalServices.ProjectTribunalServiceModifyProjectTribunals;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> removeTribunalsFromAProject(@PathVariable("id") Long projectId) {
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
    public ResponseEntity<?> assignTribunal(@RequestBody AssignTribunalsDTO assignTribunalsDTO) {
        ServiceAnswer serviceAnswer = projectTribunalServiceModifyProjectTribunals.assignTribunals(assignTribunalsDTO);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
