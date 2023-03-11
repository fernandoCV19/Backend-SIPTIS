package backend.siptis;

import backend.siptis.auth.entity.Rol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CrearRolesTest {

    @Autowired
    private


    @Test
    public void testCrearRoles(){
        Rol admin = new Rol("ADMIN");
        Rol docente = new Rol("DOCENTE");
        Rol estudiante = new Rol("ESTUDIANTE");
    }
}
