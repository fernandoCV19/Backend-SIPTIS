package backend.siptis.controller.report;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.service.report.reviewers.ReviewersReportService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Tag(name = ControllerConstants.Report.TAG_NAME, description = ControllerConstants.Report.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Report.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class ReviewersReportController {

    private final ReviewersReportService reviewersReportService;

    @Operation(summary = "Get tribunal projects report")
    @GetMapping("/reviewers/tribunal/{id}")
    @PreAuthorize("hasAuthority('TRIBUNAL')")
    public ResponseEntity<ControllerAnswer> getTribunalReport(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(reviewersReportService.getTribunalReport(id))
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get teacher projects report")
    @GetMapping("/reviewers/teacher/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<ControllerAnswer> getTeacherReport(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(reviewersReportService.getTeacherReport(id))
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get tutor projects report")
    @GetMapping("/reviewers/tutor/{id}")
    @PreAuthorize("hasAuthority('TUTOR')")
    public ResponseEntity<ControllerAnswer> getTutorReport(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(reviewersReportService.getTutorReport(id))
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get supervisor projects report")
    @GetMapping("/reviewers/supervisor/{id}")
    @PreAuthorize("hasAuthority('SUPERVISOR')")
    public ResponseEntity<ControllerAnswer> getSupervisorReport(@PathVariable("id") Long id) {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(reviewersReportService.getSupervisorReport(id))
                .message("REPORT_GENERATED").build(), null, 200);
    }
}
