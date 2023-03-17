package backend.siptis;

import backend.siptis.auth.entity.Rol;
import backend.siptis.auth.entity.Usuario;
import backend.siptis.auth.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AsignarRolesTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    public void testAsignarRolUsuario1(){
        int usuario1id = 102;
        Integer rolAdminId = 1;
        Usuario usuario1 = repository.findById(usuario1id).get();
        usuario1.addRol(new Rol(rolAdminId));

        Usuario usuarioActualizado = repository.save(usuario1);
        assertThat(usuarioActualizado.getRoles()).hasSize(1);
    }

    @Test
    public void testAsignarRolUsuario2(){
        int usuario2id = 103;
        Integer rolDocenteId = 2;
        Usuario usuario2 = repository.findById(usuario2id).get();
        usuario2.addRol(new Rol(rolDocenteId));

        Usuario usuarioActualizado = repository.save(usuario2);
        assertThat(usuarioActualizado.getRoles()).hasSize(1);
    }

    @Test
    public void testAsignarMasRolesUsuario2(){
        int usuario2id = 103;
        Integer rolDocenteId = 1;
        Usuario usuario2 = repository.findById(usuario2id).get();
        usuario2.addRol(new Rol(rolDocenteId));

        Usuario usuarioActualizado = repository.save(usuario2);
        assertThat(usuarioActualizado.getRoles()).hasSize(2);
    }
}
