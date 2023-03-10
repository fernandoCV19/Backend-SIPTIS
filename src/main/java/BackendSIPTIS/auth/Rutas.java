package BackendSIPTIS.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rutas {

    @GetMapping("/hola")
    public String verTodos(){
        return "Hola mundo";
    }

    @GetMapping("/editor")
    @PreAuthorize("hasAuthority('EDITOR')")
    public String soloEditor(){
        return "hola editor";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String soloAdmin(){
        return "hola admin";
    }
}
