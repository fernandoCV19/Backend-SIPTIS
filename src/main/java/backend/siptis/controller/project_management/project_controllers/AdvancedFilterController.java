package backend.siptis.controller.project_management.project_controllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.service.project_management.project.ProjectServiceAdvancedFilter;
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
public class AdvancedFilterController {

    private final ProjectServiceAdvancedFilter projectServiceAdvancedFilter;

    @Operation(summary = "Get projects with advanced filters")
    @GetMapping("/advanced-filter")
    public ResponseEntity<ControllerAnswer> getProjectsWithAdvancedFilters(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "6") int pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String period,
            @RequestParam(required = false) String modality,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String subarea,
            @RequestParam(required = false) String student,
            @RequestParam(required = false) String tutor
    ) {
        ServiceAnswer serviceAnswer = projectServiceAdvancedFilter.getProjectsWithAdvancedFilter(pageNumber, pageSize, name, period, modality, area, subarea, student, tutor);
        HttpStatus httpStatus = HttpStatus.OK;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
