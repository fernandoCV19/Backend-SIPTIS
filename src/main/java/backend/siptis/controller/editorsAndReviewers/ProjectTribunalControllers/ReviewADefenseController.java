package backend.siptis.controller.editorsAndReviewers.ProjectTribunalControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.editorsAndReviewers.ReviewADefenseDTO;
import backend.siptis.service.editorsAndReviewers.projectTribunalServices.ProjectTribunalServiceReviewADefense;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.ProjecTribunal.TAG_NAME, description = ControllerConstants.ProjecTribunal.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.ProjecTribunal.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class ReviewADefenseController {

    private final ProjectTribunalServiceReviewADefense projectTribunalServiceReviewADefense;

    @Operation(summary = "Review a defense given")
    @PostMapping("/reviewDefense")
    public ResponseEntity<?> reviewDefense(@RequestBody ReviewADefenseDTO reviewADefenseDTO) {
        ServiceAnswer serviceAnswer = projectTribunalServiceReviewADefense.reviewADefense(reviewADefenseDTO);
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if (serviceAnswer.getServiceMessage().equals(ServiceMessage.OK)) {
            httpStatus = HttpStatus.OK;
        }
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
