package backend.siptis.controller.projectManagement.projectControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.projectManagement.project.ProjectServiceGetNumberOfProjects;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.Project.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class GetNumberOfProjectsController {

    private final ProjectServiceGetNumberOfProjects projectServiceGetNumberOfProjects;

    @GetMapping("/modalityAndCareer/{careerId}")
    public ResponseEntity<?> getNumberOfProjectsByModalityAndCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberOfProjectsByModalityAndCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/areaAndCareer/{careerId}")
    public ResponseEntity<?> getNumberOfProjectsByAreaAndCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberOfProjectsByAreaAndCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/subareaAndCareer/{careerId}")
    public ResponseEntity<?> getNumberOfProjectsBySubAreaAndCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberOfProjectsBySubAreaAndCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/projectsByCareer/{careerId}")
    public ResponseEntity<?> getNumberProjectsByCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberProjectsByCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @GetMapping("/periodAndCareer/{careerId}")
    public ResponseEntity<?> getNumberProjectsByPeriodAndCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberProjectsByPeriodAndCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
