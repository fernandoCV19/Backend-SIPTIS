package backend.siptis.auth.rutas;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rutas {

    @GetMapping("/hola")
    public String verTodos(){
        return "Hola mundo";
    }

    @GetMapping("/docente")
    @PreAuthorize("hasAuthority('DOCENTE')")
    public String soloEditor(){
        return "hola docente";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String soloAdmin(){
        return "hola admin";
    }
}
