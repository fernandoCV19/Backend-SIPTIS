package backend.siptis.controller.editors_and_reviewers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.editors_and_reviewers.ProjectTutorService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.ProjectTutor.TAG_NAME, description = ControllerConstants.ProjectTutor.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.ProjectTutor.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class ProjectTutorController {

    private final ProjectTutorService projectTutorService;

    @Operation(summary = "Get all projects without review y tutor id")
    @GetMapping("/notReviewedProjects/{id}")
    @PreAuthorize("hasAnyAuthority('TUTOR')")
    public ResponseEntity<ControllerAnswer> getProjectsWithoutReview(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Get all projects with review y tutor id")
    @GetMapping("/reviewedProjects/{id}")
    @PreAuthorize("hasAnyAuthority('TUTOR')")
    public ResponseEntity<ControllerAnswer> getReviewedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Ge all accepted projects by tutor id")
    @GetMapping("/acceptedProjects/{id}")
    @PreAuthorize("hasAnyAuthority('TUTOR')")
    public ResponseEntity<ControllerAnswer> getAcceptedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTutorService.getAllProjectsAcceptedByTutorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Accept a project")
    @PostMapping("/acceptProject/{idProject}/{idReviewer}")
    @PreAuthorize("hasAnyAuthority('TUTOR')")
    public ResponseEntity<ControllerAnswer> acceptProject(@PathVariable("idProject") Long idProject, @PathVariable("idReviewer") Long idReviewer) {
        ServiceAnswer serviceAnswer = projectTutorService.acceptProject(idReviewer, idProject);
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
