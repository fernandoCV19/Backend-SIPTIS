package backend.siptis.auth.rutas;

import backend.siptis.auth.entity.Contacto;
import backend.siptis.auth.entity.Rol;
import backend.siptis.auth.entity.Usuario;
import backend.siptis.auth.repository.ContactoRepository;
import backend.siptis.auth.repository.RolRepository;
import backend.siptis.auth.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class Rutas {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final ContactoRepository contactoRepository;



    @GetMapping("/todos")

    public List<Contacto> verTodos(){
        Rol usuario1 = new Rol("uno");
        Rol usuario2 = new Rol("dos");
        Rol usuario3 = new Rol("tres");
        List<Rol> roles = new ArrayList<>();
        roles.add(usuario1);
        roles.add(usuario2);
        roles.add(usuario3);
        List<Rol> rols = rolRepository.findAll();
        System.out.println(rols.size());
        for (Rol user : rols ) {
            System.out.println(user);
        }
        return contactoRepository.findAll();
    }

    @GetMapping("/hola")
    public List<Usuario> verTodosAuth(){
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
