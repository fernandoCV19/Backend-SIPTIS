package backend.siptis.controller.editorsAndReviewers.ProjectTribunalControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.editorsAndReviewers.projectTribunalServices.ProjectTribunalServiceAcceptProject;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.ProjecTribunal.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class AcceptProjectController {

    private final ProjectTribunalServiceAcceptProject tribunalServiceAcceptProject;

    @GetMapping("/acceptProject/{idProject}/{idReviewer}")
    public ResponseEntity<?> acceptProject(@PathVariable("idProject") Long idProject, @PathVariable("idReviewer") Long idReviewer) {
        ServiceAnswer serviceAnswer = tribunalServiceAcceptProject.acceptProject(idReviewer, idProject);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
