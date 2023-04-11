package backend.siptis.controller.editorsAndReviewers;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.editorsAndReviewers.ProjectSupervisorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/supervisor")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectSupervisorController {

    private final ProjectSupervisorService projectSupervisorService;

    @GetMapping("/notReviewedProjects/{id}")
    public ResponseEntity<?> getProjectWithoutReview(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectSupervisorService.getAllProjectsNotAcceptedNotReviewedBySupervisorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/reviewedProjects/{id}")
    public ResponseEntity<?> getReviewedProjects(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectSupervisorService.getAllProjectsNotAcceptedReviewedBySupervisorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/acceptedProjects/{id}")
    public ResponseEntity<?> getAcceptedProjects(@PathVariable("id") Long id){
        ServiceAnswer serviceAnswer = projectSupervisorService.getAllProjectsAcceptedBySupervisorId(id);
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
