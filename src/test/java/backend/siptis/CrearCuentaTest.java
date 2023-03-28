package backend.siptis;

import backend.siptis.auth.entity.SiptisUser;
import backend.siptis.model.repository.datosUsuario.UsuarioRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;


import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CrearCuentaTest {


    @Autowired
    private UsuarioRepository repo;

    @Test
    public void testCrearUsuario1(){

        String email = "admin@gmail.com";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String contrasena = passwordEncoder.encode("123");

        SiptisUser siptisUser = new SiptisUser(email,contrasena);

        SiptisUser siptisUserGuardado = repo.save(siptisUser);

        assertThat(siptisUserGuardado).isNotNull();
        assertThat(siptisUserGuardado.getId()).isGreaterThan(0);

    }

    @Test
    public void testCrearUsuario2(){

        String email = "maury.vargasl@gmail.com";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String contrasena = passwordEncoder.encode("mavl");

        SiptisUser siptisUser = new SiptisUser(email,contrasena);

        SiptisUser siptisUserGuardado = repo.save(siptisUser);

        assertThat(siptisUserGuardado).isNotNull();
        assertThat(siptisUserGuardado.getId()).isGreaterThan(0);

    }
}
