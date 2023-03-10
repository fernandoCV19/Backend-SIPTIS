package BackendSIPTIS.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rutas {

    @GetMapping("/hola")
    public String verTodos(){
        return "Hola mundo";
    }

    @GetMapping("/editor")
    public String soloEditor(){
        return "hola editor";
    }

    @GetMapping("/admin")
    public String soloAdmin(){
        return "hola admin";
    }
}
