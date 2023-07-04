package backend.siptis.controller.userData.UserInformation;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import backend.siptis.service.userData.searchUsers.SearchUsers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/siptis")
@AllArgsConstructor
@CrossOrigin
public class ListOfUsersController {

    @Autowired
    private final SearchUsers searchUsersService;

    @GetMapping("/adminList")
    public ResponseEntity<?> adminList(){
        ServiceAnswer answer = searchUsersService.getAllAdmin();

        return crearResponseEntityRegistrar(answer);
    }

    @GetMapping("/teacherList")
    public ResponseEntity<?> teacherList(){
        ServiceAnswer answer = searchUsersService.getAllTeacher();

        return crearResponseEntityRegistrar(answer);
    }

    @GetMapping("/studentList")
    public ResponseEntity<?> studentList(){
        System.out.println('s');
        ServiceAnswer answer = searchUsersService.getAllStudent();

        return crearResponseEntityRegistrar(answer);
    }

    @GetMapping("/buscarUser/{role_id}/{name}")
    public ResponseEntity<?> searchUsers( @PathVariable Long role_id, @PathVariable String name){
        System.out.println('s');
        ServiceAnswer answer = searchUsersService.searchUserByNameAndRole(name, role_id);

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
}
