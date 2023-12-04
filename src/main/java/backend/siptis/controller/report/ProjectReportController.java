package backend.siptis.controller.report;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.service.report.projects.ProjectReportService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = ControllerConstants.Report.TAG_NAME, description = ControllerConstants.Report.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Report.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class ProjectReportController {

    private final ProjectReportService projectReportService;

    @Operation(summary = "Get project report about tribunals")
    @GetMapping("/project/tribunal")
    public ResponseEntity<ControllerAnswer> getProjectTribunalReport() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(projectReportService.getProjectTribunalReport())
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get projects filter by career report")
    @GetMapping("/project/career")
    public ResponseEntity<ControllerAnswer> getProjectFilterByCareerReport() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(projectReportService.getProjectCareerReport())
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get projects filter by areas report")
    @GetMapping("/project/areas")
    public ResponseEntity<ControllerAnswer> getProjectFilterByAreasReport() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(projectReportService.getProjectAreaReport())
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get projects filter by sub areas report")
    @GetMapping("/project/subareas")
    public ResponseEntity<ControllerAnswer> getProjectFilterBySubAreasReport() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(projectReportService.getProjectSubAreaReport())
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get projects filter by phases report")
    @GetMapping("/project/phases")
    public ResponseEntity<ControllerAnswer> getProjectFilterByPhasesReport() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(projectReportService.getProjectPhaseReport())
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get complete project report")
    @GetMapping("/project/complete")
    public ResponseEntity<ControllerAnswer> getCompleteProjectReport() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(projectReportService.getCompleteProjectReport())
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get projects with state report")
    @GetMapping("/project/state")
    public ResponseEntity<ControllerAnswer> getProjectStateReport() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(projectReportService.getActiveAndInactiveProjects())
                .message("REPORT_GENERATED").build(), null, 200);
    }
}
