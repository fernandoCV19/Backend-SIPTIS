package backend.siptis.controller.userData;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.userData.ScheduleService;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(ControllerConstants.Schedule.BASE_PATH)
@AllArgsConstructor
@Getter
@Setter
public class ScheduleController {

    private final ScheduleService scheduleService;

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
