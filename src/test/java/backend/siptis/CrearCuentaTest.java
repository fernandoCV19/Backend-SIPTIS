package backend.siptis;

import backend.siptis.auth.entity.Usuario;
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
        String nombres = "ADMIN";
        String apellidos = "M";
        String celular = "4";
        String ci = "111";
        String email = "admin@gmail.com";
        String codSIS = "2019";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String contrasena = passwordEncoder.encode("123");

        Usuario usuario = new Usuario(nombres,apellidos,celular,
                ci,email,contrasena,codSIS);

        Usuario usuarioGuardado = repo.save(usuario);

        assertThat(usuarioGuardado).isNotNull();
        assertThat(usuarioGuardado.getId()).isGreaterThan(0);

    }

    @Test
    public void testCrearUsuario2(){
        String nombres = "Mauricio";
        String apellidos = "Vargas";
        String celular = "7777777";
        String ci = "123";
        String email = "maury.vargasl@gmail.com";
        String codSIS = "201900188";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String contrasena = passwordEncoder.encode("mavl");

        Usuario usuario = new Usuario(nombres,apellidos,celular,
                ci,email,contrasena,codSIS);

        Usuario usuarioGuardado = repo.save(usuario);

        assertThat(usuarioGuardado).isNotNull();
        assertThat(usuarioGuardado.getId()).isGreaterThan(0);

    }
}
