package backend.siptis.controller.editorsAndReviewers;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.editorsAndReviewers.ProjectTutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tutor")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectTutorController {

    private final ProjectTutorService projectTutorService;

    @GetMapping("/notReviewedProjects/{id}")
    public ResponseEntity<?> getProjectsWithoutReview(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/reviewedProjects/{id}")
    public ResponseEntity<?> getReviewedProjects(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/acceptedProjects/{id}")
    public ResponseEntity<?> getAcceptedProjects(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectTutorService.getAllProjectsAcceptedByTutorId(id);
        return createResponseEntity(serviceAnswer);
    }

    private ResponseEntity<?> createResponseEntity(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage serviceMessage = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.OK;

        if(serviceMessage == ServiceMessage.ID_DOES_NOT_EXIST)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(serviceMessage.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
