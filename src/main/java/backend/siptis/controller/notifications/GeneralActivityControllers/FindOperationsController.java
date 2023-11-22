package backend.siptis.controller.notifications.GeneralActivityControllers;


import backend.siptis.commons.ControllerAnswer;
import backend.siptis.service.notifications.generalActivityServices.GeneralActivityServiceFindOperations;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerConstants.GeneralActivity.BASE_PATH)
@CrossOrigin
@RequiredArgsConstructor
public class FindOperationsController {

    private final GeneralActivityServiceFindOperations generalActivityServiceFindOperations;

    @GetMapping()
    public ResponseEntity<ControllerAnswer> findAllGeneralActivity(Pageable pageable) {
        return new ResponseEntity<>(
                ControllerAnswer.builder()
                        .data(generalActivityServiceFindOperations.findAll(pageable))
                        .message("General activities found successfully")
                        .build(), null, 200);
    }
}
