package backend.siptis;

import backend.siptis.auth.entity.User;
import backend.siptis.auth.repository.UsuarioRepository;

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

        User user = new User(email,contrasena);

        User userGuardado = repo.save(user);

        assertThat(userGuardado).isNotNull();
        assertThat(userGuardado.getId()).isGreaterThan(0);

    }

    @Test
    public void testCrearUsuario2(){

        String email = "maury.vargasl@gmail.com";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String contrasena = passwordEncoder.encode("mavl");

        User user = new User(email,contrasena);

        User userGuardado = repo.save(user);

        assertThat(userGuardado).isNotNull();
        assertThat(userGuardado.getId()).isGreaterThan(0);

    }
}
