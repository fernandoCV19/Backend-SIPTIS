package backend.siptis.auth.jwt;

import backend.siptis.auth.security.Credentials;
import backend.siptis.service.userData.UserInformationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response)  {
        Credentials authCredentials =new Credentials();
        try {
            System.out.println("esta intentando");
            authCredentials = new ObjectMapper().readValue(
                    request.getReader(), Credentials.class
            );
        } catch (Exception e){
            //System.out.println("excepcion: "+ e.getMessage());
        }

        try {
            UsernamePasswordAuthenticationToken PAToken =
                    new UsernamePasswordAuthenticationToken(
                            authCredentials.getEmail(),
                            authCredentials.getPassword(),
                            Collections.emptyList());
            System.out.println(authCredentials);
            System.out.println("CORREO: "+authCredentials.getEmail());
            System.out.println("CONTRASENA: "+authCredentials.getPassword());
            return getAuthenticationManager().authenticate(PAToken);
        }catch (
    AuthenticationException e){
            System.out.println("Error de autenticacion: "+e.getMessage());
            return null;
        }
    }


    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication authentication
    ) throws IOException {
        System.out.println("ENTRA AL SUCCESS ");
        UserInformationService.UserDetailImp userDetails= (UserInformationService.UserDetailImp) authentication.getPrincipal();
        String token = JWTokenUtils.createToken(userDetails);
        System.out.println("token: "+token);
        response.addHeader("Authorization", "Bearer "+ token);
        response.getWriter().flush();
    }

}
