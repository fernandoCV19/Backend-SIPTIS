package backend.siptis.controller.report;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.service.report.ReportService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.Report.TAG_NAME, description = ControllerConstants.Report.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Report.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "Get tribunal report")
    @GetMapping("/tribunal/general")
    public ResponseEntity<ControllerAnswer> getGeneralTribunalReport() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(reportService.getGeneralTribunalReport())
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get presentation reviews report")
    @GetMapping("/review/{presentationId}")
    public ResponseEntity<ControllerAnswer> getReviewsReport(@PathVariable(value = "presentationId") Long presentationId) {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(reportService.getReviewSummaryReport(presentationId))
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get presentations report")
    @GetMapping("/presentation/{projectId}")
    public ResponseEntity<ControllerAnswer> getPresentationsReport(@PathVariable(value = "projectId") Long projectId) {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(reportService.getPresentationReport(projectId))
                .message("REPORT_GENERATED").build(), null, 200);
    }

}
