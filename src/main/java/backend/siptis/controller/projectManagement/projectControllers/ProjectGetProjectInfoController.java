package backend.siptis.controller.projectManagement.projectControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.projectManagement.project.ProjectServiceGetProjectInfo;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.Project.TAG_NAME, description = ControllerConstants.Project.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Project.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class ProjectGetProjectInfoController {

    private final ProjectServiceGetProjectInfo projectServiceGetProjectInfo;

    @Operation(summary = "Get project information by project id")
    @GetMapping("/information/{id}")
    public ResponseEntity<?> getProjectInformation(@PathVariable("id") Long idProject) {
        ServiceAnswer serviceAnswer = projectServiceGetProjectInfo.getProjectInfo(idProject);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK && serviceAnswer.getServiceMessage() != ServiceMessage.THERE_IS_NO_PRESENTATION_YET) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get project information by project id and reviewer id")
    @GetMapping("/getProjectInfoToReview/{projectId}/{reviewerId}")
    public ResponseEntity<?> getProjectInfoToReview(@PathVariable("projectId") Long projectId, @PathVariable("reviewerId") Long reviewerId) {
        ServiceAnswer serviceAnswer = projectServiceGetProjectInfo.getProjectInfoToReview(projectId, reviewerId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK && serviceAnswer.getServiceMessage() != ServiceMessage.THERE_IS_NO_PRESENTATION_YET) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get all project information by project id")
    @GetMapping("/allInfo/{projectId}")
    public ResponseEntity<?> getAllInfo(@PathVariable("projectId") Long projectId) {
        ServiceAnswer serviceAnswer = projectServiceGetProjectInfo.getAllProjectInfo(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get project assign tribunals by project id")
    @GetMapping("/getInfoToAssignTribunals/{projectId}")
    public ResponseEntity<?> getInfoToAssignTribunals(@PathVariable("projectId") Long projectId) {
        ServiceAnswer serviceAnswer = projectServiceGetProjectInfo.getProjectInfoToAssignTribunals(projectId);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
