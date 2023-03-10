package BackendSIPTIS;

import BackendSIPTIS.auth.entity.Usuario;
import BackendSIPTIS.auth.repository.UsuarioRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CrearCuentaTest {


    @Autowired
    private UsuarioRepository repo;

    @Test
    public void crearUsuario1() throws JsonProcessingException, Exception{
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
