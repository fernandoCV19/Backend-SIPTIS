package backend.siptis;

import backend.siptis.auth.entity.Role;
import backend.siptis.auth.repository.RolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CrearRolesTest {

    @Autowired
    private RolRepository repository;


    @Test
    public void testCrearRoles(){
        Role admin = new Role("ADMIN");
        Role docente = new Role("DOCENTE");
        Role estudiante = new Role("ESTUDIANTE");
        repository.save(admin);
        repository.save(docente);
        repository.save(estudiante);

        long numeroRoles = repository.count();
        assertEquals(3, numeroRoles);
    }
}
