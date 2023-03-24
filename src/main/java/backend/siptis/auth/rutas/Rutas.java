package backend.siptis.auth.rutas;

import backend.siptis.auth.entity.Contacto;
import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.auth.repository.ContactoRepository;
import backend.siptis.auth.repository.RolRepository;
import backend.siptis.auth.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class Rutas {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final ContactoRepository contactoRepository;



    @GetMapping("/todos")

    public List<Contacto> verTodos(){

        List<Contacto> lista  = contactoRepository.findAll();
        return lista;
    }

    @GetMapping("/contactos")
    @PreAuthorize("hasAuthority('DOCENTE')")
    public List<Contacto> verContactos(){
        List<Contacto> lista  = contactoRepository.findAll();
        return lista;
    }

    @GetMapping("/hola")
    public List<SiptisUser> verTodosAuth(){
        return usuarioRepository.findAll();
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
