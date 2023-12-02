package backend.siptis.controller.report;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.service.report.activities.ActivitiesReportService;
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
public class ActivitiesReportController {

    private final ActivitiesReportService activitiesReportService;

    @Operation(summary = "Get activites report")
    @GetMapping("/activities/general")
    public ResponseEntity<ControllerAnswer> getGeneralActivitiesReport(){
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(activitiesReportService.getGeneralActivitesReport())
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get activities by project")
    @GetMapping("/activities/project")
    public ResponseEntity<ControllerAnswer> getActivitiesByProject() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(activitiesReportService.getActivitiesByProject())
                .message("REPORT_GENERATED").build(), null, 200);
    }
}
