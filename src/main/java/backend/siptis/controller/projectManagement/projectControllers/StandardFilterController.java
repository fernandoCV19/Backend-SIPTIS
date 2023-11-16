package backend.siptis.controller.projectManagement.projectControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.projectManagement.project.ProjectServiceStandardFilter;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ControllerConstants.Project.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class StandardFilterController {

    private final ProjectServiceStandardFilter projectServiceStandardFilter;

    @GetMapping("/standard-filter")
    public ResponseEntity<?> getPaginatedProjectsByFilter(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "6") int pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String period
    ) {
        ServiceAnswer serviceAnswer = projectServiceStandardFilter.getProjectsWithStandardFilter(pageNumber, pageSize, name, period);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
