package backend.siptis.controller.editorsAndReviewers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.editorsAndReviewers.ProjectTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectTeacherController {

    private final ProjectTeacherService projectTeacherService;

    @GetMapping("/notReviewedProjects/{id}")
    public ResponseEntity<?> getProjectsWithoutReview(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTeacherService.getAllProjectsNotAcceptedNotReviewedByTeacherId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/reviewedProjects/{id}")
    public ResponseEntity<?> getReviewedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTeacherService.getAllProjectsNotAcceptedReviewedByTeacherId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/acceptedProjects/{id}")
    public ResponseEntity<?> getAcceptedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTeacherService.getAllProjectsAcceptedByTeacherId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/acceptProject/{idProject}/{idReviewer}")
    public ResponseEntity<?> acceptProject(@PathVariable("idProject") Long idProject, @PathVariable("idReviewer") Long idReviewer) {
        ServiceAnswer serviceAnswer = projectTeacherService.acceptProject(idReviewer, idProject);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer) {
        Object data = serviceAnswer.getData();
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if (serviceMessage == ServiceMessage.ID_DOES_NOT_EXIST)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(serviceMessage.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
