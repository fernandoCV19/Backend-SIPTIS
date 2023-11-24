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
    @GetMapping("/modalityAndCareer/{career}")
    public ResponseEntity<ControllerAnswer> getNumberOfProjectsByModalityAndCareer(@PathVariable String career) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberOfProjectsByModalityAndCareer(career);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get number of projects by area and career id")
    @GetMapping("/areaAndCareer/{career}")
    public ResponseEntity<ControllerAnswer> getNumberOfProjectsByAreaAndCareer(@PathVariable String career) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberOfProjectsByAreaAndCareer(career);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get number of projects by sub area and career")
    @GetMapping("/subareaAndCareer/{career}")
    public ResponseEntity<ControllerAnswer> getNumberOfProjectsBySubAreaAndCareer(@PathVariable String career) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberOfProjectsBySubAreaAndCareer(career);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get number of projects by career id")
    @GetMapping("/projectsByCareer/{career}")
    public ResponseEntity<ControllerAnswer> getNumberProjectsByCareer(@PathVariable String career) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberProjectsByCareer(career);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @Operation(summary = "Get number of projects by period and career id")
    @GetMapping("/periodAndCareer/{career}")
    public ResponseEntity<ControllerAnswer> getNumberProjectsByPeriodAndCareer(@PathVariable String career) {
        ServiceAnswer serviceAnswer = projectServiceGetNumberOfProjects.getNumberProjectsByPeriodAndCareer(career);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
