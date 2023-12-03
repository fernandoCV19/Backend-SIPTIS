package backend.siptis.controller.report;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.service.report.siptis_user.SiptisUserReportService;
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
public class SiptisUserReportController {

    private final SiptisUserReportService siptisUserReportService;

    @Operation(summary = "Get student report")
    @GetMapping("/student")
    public ResponseEntity<ControllerAnswer> getStudentReport() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(siptisUserReportService.getStudentReport())
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get admin report")
    @GetMapping("/admin")
    public ResponseEntity<ControllerAnswer> getAdminReport() {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(siptisUserReportService.getAdminReport())
                .message("REPORT_GENERATED").build(), null, 200);
    }

    @Operation(summary = "Get general user report")
    @GetMapping("/user/{role}")
    public ResponseEntity<ControllerAnswer> getGeneralUserReport(@PathVariable String role) {
        return new ResponseEntity<>(ControllerAnswer.builder()
                .data(siptisUserReportService.getGeneralUserReport(role))
                .message("REPORT_GENERATED").build(), null, 200);
    }
}
