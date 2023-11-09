package backend.siptis.controller.editorsAndReviewers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.entity.editorsAndReviewers.ProjectTutor;
import backend.siptis.service.editorsAndReviewers.ProjectTutorService;
import backend.siptis.service.userData.SiptisUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutor")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectTutorController {

    private final ProjectTutorService projectTutorService;
    private final SiptisUserService userService;

    @GetMapping("/notReviewedProjects/{id}")
    public ResponseEntity<?> getProjectsWithoutReview(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTutorService.getAllProjectsNotAcceptedNotReviewedByTutorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/reviewedProjects/{id}")
    public ResponseEntity<?> getReviewedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTutorService.getAllProjectsNotAcceptedReviewedByTutorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/acceptedProjects/{id}")
    public ResponseEntity<?> getAcceptedProjects(@PathVariable("id") Long id) {
        ServiceAnswer serviceAnswer = projectTutorService.getAllProjectsAcceptedByTutorId(id);
        return createResponseEntity(serviceAnswer);
    }

    @GetMapping("/acceptProject/{idProject}/{idReviewer}")
    public ResponseEntity<?> acceptProject(@PathVariable("idProject") Long idProject, @PathVariable("idReviewer") Long idReviewer) {
        ServiceAnswer serviceAnswer = projectTutorService.acceptProject(idReviewer, idProject);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/removeAccepted/{idProject}/{idReviewer}")
    public ResponseEntity<?> removeacceptedFromAProject(@PathVariable("idProject") Long idProject, @PathVariable("idReviewer") Long idReviewer) {
        ServiceAnswer serviceAnswer = projectTutorService.removeAcceptProject(idReviewer, idProject);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/allTutoringProjects")
    public ResponseEntity<?> getAllTutoringProjects(){
        //@RequestHeader(name = "Authorization") String token
        //Long idL = userService.getIdFromToken(token);
        ServiceAnswer projectTutorList = projectTutorService.getAllTutorProject(103L);
        HttpStatus httpStatus = HttpStatus.OK;
        return  createResponseEntity(projectTutorList);
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
