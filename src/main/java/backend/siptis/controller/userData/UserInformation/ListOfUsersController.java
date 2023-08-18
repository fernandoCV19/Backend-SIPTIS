package backend.siptis.controller.userData.UserInformation;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.userData.UserInformationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/siptis")
@AllArgsConstructor
@CrossOrigin
public class ListOfUsersController {
/*
    private final UserInformationService userInformationService;

    @GetMapping("/adminList")
    public ResponseEntity<?> adminList(){
        ServiceAnswer answer = userInformationService.getAllAdmin();

        return crearResponseEntityRegistrar(answer);
    }

    @GetMapping("/teacherList")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> teacherList(){
        ServiceAnswer answer = userInformationService.getAllTeachers();

        return crearResponseEntityRegistrar(answer);
    }

    @GetMapping("/studentList")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> studentList(){
        ServiceAnswer answer = userInformationService.getAllStudents();

        return crearResponseEntityRegistrar(answer);
    }

    @GetMapping("/buscarUser/{role_id}/{name}")
    public ResponseEntity<?> searchUsers( @PathVariable Long role_id, @PathVariable String name){
        ServiceAnswer answer = userInformationService.searchUserByNameAndRole(name, role_id);

        return crearResponseEntityRegistrar(answer);
    }

    private ResponseEntity<?> crearResponseEntityRegistrar(ServiceAnswer serviceAnswer){
        Object data = serviceAnswer.getData();
        ServiceMessage messageService = serviceAnswer.getServiceMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        if(messageService == ServiceMessage.OK){
            httpStatus = HttpStatus.OK;
        }


        if(messageService == ServiceMessage.NOT_FOUND || messageService == ServiceMessage.ERROR)
            httpStatus = HttpStatus.NOT_FOUND;

        ControllerAnswer controllerAnswer = ControllerAnswer.builder().data(data).message(messageService.toString()).build();
        return new ResponseEntity<>(controllerAnswer, httpStatus);
    }

 */
}
