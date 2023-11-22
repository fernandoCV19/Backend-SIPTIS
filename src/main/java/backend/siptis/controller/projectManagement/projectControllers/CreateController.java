package backend.siptis.controller.projectManagement.projectControllers;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.pjo.dto.projectManagement.NewProjectDTO;
import backend.siptis.service.projectManagement.project.ProjectServiceCreate;
import backend.siptis.utils.constant.controllerConstans.ControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = ControllerConstants.Project.TAG_NAME, description = ControllerConstants.Project.TAG_DESCRIPTION)
@RestController
@RequestMapping(ControllerConstants.Project.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin
public class CreateController {

    private final ProjectServiceCreate projectServiceCreate;

    @Operation(summary = "Creation of a new project")
    @PostMapping("/newProject")
    public ResponseEntity<?> createProject(@Valid @RequestBody NewProjectDTO dto) {
        ServiceAnswer serviceAnswer = projectServiceCreate.createProject(dto);

        HttpStatus httpStatus = HttpStatus.OK;
        if (serviceAnswer.getServiceMessage() != ServiceMessage.OK)
            httpStatus = HttpStatus.BAD_REQUEST;
        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(serviceAnswer.getData()).message(serviceAnswer.getServiceMessage().toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }
}
