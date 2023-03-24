package backend.siptis;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.entity.SiptisUser;
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
        SiptisUser siptisUser1 = repository.findById(usuario1id).get();
        siptisUser1.addRol(new Role(rolAdminId));

        SiptisUser siptisUserActualizado = repository.save(siptisUser1);
        assertThat(siptisUserActualizado.getRoles()).hasSize(1);
    }

    @Test
    public void testAsignarRolUsuario2(){
        int usuario2id = 103;
        Integer rolDocenteId = 2;
        SiptisUser siptisUser2 = repository.findById(usuario2id).get();
        siptisUser2.addRol(new Role(rolDocenteId));

        SiptisUser siptisUserActualizado = repository.save(siptisUser2);
        assertThat(siptisUserActualizado.getRoles()).hasSize(1);
    }

    @Test
    public void testAsignarMasRolesUsuario2(){
        int usuario2id = 103;
        Integer rolDocenteId = 1;
        SiptisUser siptisUser2 = repository.findById(usuario2id).get();
        siptisUser2.addRol(new Role(rolDocenteId));

        SiptisUser siptisUserActualizado = repository.save(siptisUser2);
        assertThat(siptisUserActualizado.getRoles()).hasSize(2);
    }
}
