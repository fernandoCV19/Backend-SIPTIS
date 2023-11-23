package backend.siptis.controller.project_management.project_controllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.project_management.project.ProjectServiceStandardFilter;
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
public class StandardFilterController {

    private final ProjectServiceStandardFilter projectServiceStandardFilter;

    @Operation(summary = "Get projects with standard filters")
    @GetMapping("/standard-filter")
    public ResponseEntity<ControllerAnswer> getPaginatedProjectsByFilter(
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
