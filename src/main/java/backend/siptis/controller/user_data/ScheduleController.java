package backend.siptis.controller.user_data;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.user_data.ScheduleService;
import backend.siptis.utils.constant.controller_constans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = ControllerConstants.Schedule.TAG_NAME, description = ControllerConstants.Schedule.TAG_DESCRIPTION)
@Controller
@RequestMapping(ControllerConstants.Schedule.BASE_PATH)
@AllArgsConstructor
@Getter
@Setter
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Operation(summary = "Get all schedules from a project")
    @GetMapping("/all/{id}")
    public ResponseEntity<ControllerAnswer> getAllSchedulesFromAProject(@PathVariable Long id) {
        ServiceAnswer serviceAnswer = scheduleService.getAllSchedulesFromAProject(id);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
