package backend.siptis.controller.defenseManagement;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.projectManagement.DefenseDTO;
import backend.siptis.service.defenseManagement.DefenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/defense")
@CrossOrigin
public class DefenseController {

    @Autowired
    private DefenseService defenseService;

    @GetMapping("/defensesByMonth/{month}")
    public ResponseEntity<?> getPlaceReservationsByMonth(@PathVariable("month") Integer month) {
        ServiceAnswer serviceAnswer = defenseService.getDefenseByMonth(month);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @PostMapping("/createDefense")
    public ResponseEntity<?> createDefense(@RequestBody DefenseDTO defenseDTO) {
        ServiceAnswer serviceAnswer = defenseService.registerDefense(defenseDTO);
        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

    @DeleteMapping("/removeDefense/{id}")
    public ResponseEntity<?> removeDefenseFromAProject(@PathVariable("id") Long projectId) {
        ServiceAnswer serviceAnswer = defenseService.removeDefense(projectId);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
