package backend.siptis.controller.project_management.project_controllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.project_management.project.ProjectServiceGetNumberOfProjects;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.Project.TAG_NAME, description = ControllerConstants.Project.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Project.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class GetNumberOfProjectsController {

    private final ProjectServiceGetNumberOfProjects projectServiceGetNumberOfProjects;

    @Operation(summary = "Get number of project by modality and career id ")
    @GetMapping("/modalityAndCareer/{careerId}")
    public ResponseEntity<ControllerAnswer> getNumberOfProjectsByModalityAndCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberOfProjectsByModalityAndCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get number of projects by area and career id")
    @GetMapping("/areaAndCareer/{careerId}")
    public ResponseEntity<ControllerAnswer> getNumberOfProjectsByAreaAndCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberOfProjectsByAreaAndCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get number of projets by sub area and career")
    @GetMapping("/subareaAndCareer/{careerId}")
    public ResponseEntity<ControllerAnswer> getNumberOfProjectsBySubAreaAndCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberOfProjectsBySubAreaAndCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get number of projects by career id")
    @GetMapping("/projectsByCareer/{careerId}")
    public ResponseEntity<ControllerAnswer> getNumberProjectsByCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberProjectsByCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get number of projects by period and cereer id")
    @GetMapping("/periodAndCareer/{careerId}")
    public ResponseEntity<ControllerAnswer> getNumberProjectsByPeriodAndCareer(@PathVariable Long careerId) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberProjectsByPeriodAndCareer(careerId);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
