package backend.siptis.controller.editorsAndReviewers.ProjectTribunalControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.editorsAndReviewers.projectTribunalServices.ProjectTribunalServiceGetProjects;
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
public class GetProjectsController {

    private final ProjectTribunalServiceGetProjects projectTribunalServiceGetProjects;

    @Operation(summary = "Get all projects without review by tribunal id")
    @GetMapping("/notReviewedProjects/{id}")
    public ResponseEntity<ControllerAnswer> getProjectsWithoutReview(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTribunalServiceGetProjects.getAllProjectsNotAcceptedNotReviewedByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Get all projects with review by tribunal id")
    @GetMapping("/reviewedProjects/{id}")
    public ResponseEntity<ControllerAnswer> getReviewedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTribunalServiceGetProjects.getAllProjectsNotAcceptedReviewedByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }


    @Operation(summary = "Get all projects accepted without defense points by tribunal id")
    @GetMapping("/acceptedProjects/{id}")
    public ResponseEntity<ControllerAnswer> getProjectsReadyToDefense(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTribunalServiceGetProjects.getAllProjectsAcceptedWithoutDefensePointsByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Get all projects defended by tribunal id")
    @GetMapping("/defendedProjects/{id}")
    public ResponseEntity<ControllerAnswer> getdefendedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTribunalServiceGetProjects.getAllProjectsDefendedByTribunalId(id);
        return createResponseEntity(serviceAnswer);
    }

    private ResponseEntity<ControllerAnswer> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if (serviceMessage == ServiceMessage.ID_DOES_NOT_EXIST)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(serviceMessage.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
