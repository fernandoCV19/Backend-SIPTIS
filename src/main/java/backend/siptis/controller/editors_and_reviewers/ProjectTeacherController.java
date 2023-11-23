package backend.siptis.controller.editors_and_reviewers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.editors_and_reviewers.ProjectTeacherService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.ProjectTeacher.TAG_NAME, description = ControllerConstants.ProjectTeacher.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.ProjectTeacher.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class ProjectTeacherController {

    private final ProjectTeacherService projectTeacherService;

    @Operation(summary = "Get not accepted projects without teacher review")
    @GetMapping("/notReviewedProjects/{id}")
    public ResponseEntity<ControllerAnswer> getProjectsWithoutReview(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(id);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Get not accepted projects with teacher review")
    @GetMapping("/reviewedProjects/{id}")
    public ResponseEntity<ControllerAnswer> getReviewedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(id);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Get all accepted projects by teacher id")
    @GetMapping("/acceptedProjects/{id}")
    public ResponseEntity<ControllerAnswer> getAcceptedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTeacherService.getAllProjectsAcceptedByTeacherId(id);
        return createResponseEntity(serviceAnswer);
    }

    @Operation(summary = "Get all accepted projects by project id and teacher id")
    @GetMapping("/acceptProject/{idProject}/{idReviewer}")
    public ResponseEntity<ControllerAnswer> acceptProject(@PathVariable("idProject") Long idProject, @PathVariable("idReviewer") Long idReviewer) {
        ServiceAnswer serviceAnswer = projectTeacherService.acceptProject(idReviewer, idProject);
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
