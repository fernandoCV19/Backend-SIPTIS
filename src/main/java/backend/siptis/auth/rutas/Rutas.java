package backend.siptis.auth.rutas;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Rutas {



    /*@GetMapping("/todos")
    public List<Contacto> verTodos(){

        List<Contacto> lista  = contactoRepository.findAll();
        return lista;
    }*/


    @GetMapping("/docente")
    @PreAuthorize("hasAuthority('DOCENTE')")
    public String soloEditor() {
        return "hola docente";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String soloAdmin() {
        return "hola admin";
    }
}
