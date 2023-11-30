package backend.siptis.controller.editors_and_reviewers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.editors_and_reviewers.ProjectSupervisorService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.ProjectSupervisor.TAG_NAME, description = ControllerConstants.ProjectSupervisor.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.ProjectSupervisor.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class ProjectSupervisorController {

    private final ProjectSupervisorService projectSupervisorService;

    @Operation(summary = "Get projects without supervisor review")
    @GetMapping("/notReviewedProjects/{id}")
    @PreAuthorize("hasAnyAuthority('SUPERVISOR')")
    public ResponseEntity<ControllerAnswer> getProjectWithoutReview(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Get not accepted projects with supervisor review")
    @GetMapping("/reviewedProjects/{id}")
    @PreAuthorize("hasAnyAuthority('SUPERVISOR')")
    public ResponseEntity<ControllerAnswer> getReviewedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Get all projects accepted by supervisor id")
    @GetMapping("/acceptedProjects/{id}")
    @PreAuthorize("hasAnyAuthority('SUPERVISOR')")
    public ResponseEntity<ControllerAnswer> getAcceptedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Get all accepted projects by project id and supervisor id")
    @GetMapping("/acceptProject/{idProject}/{idReviewer}")
    @PreAuthorize("hasAnyAuthority('SUPERVISOR')")
    public ResponseEntity<ControllerAnswer> acceptProject(@PathVariable("idProject") Long idProject, @PathVariable("idReviewer") Long idReviewer) {
        ServiceAnswer serviceAnswer = projectSupervisorService.acceptProject(idReviewer, idProject);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
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
