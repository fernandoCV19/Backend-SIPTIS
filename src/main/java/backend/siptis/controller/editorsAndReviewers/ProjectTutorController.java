package backend.siptis.controller.editorsAndReviewers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.editorsAndReviewers.ProjectTutorService;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.ProjectTutor.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class ProjectTutorController {

    private final ProjectTutorService projectTutorService;

    @GetMapping("/notReviewedProjects/{id}")
    public ResponseEntity<ControllerAnswer> getProjectsWithoutReview(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/reviewedProjects/{id}")
    public ResponseEntity<ControllerAnswer> getReviewedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/acceptedProjects/{id}")
    public ResponseEntity<ControllerAnswer> getAcceptedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTutorService.getAllProjectsAcceptedByTutorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/acceptProject/{idProject}/{idReviewer}")
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
