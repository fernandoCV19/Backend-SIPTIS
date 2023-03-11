package backend.siptis.auth.jwt;

import backend.siptis.auth.security.Credentials;
import backend.siptis.auth.service.UserDetailImp;
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
            HttpServletResponse response) throws AuthenticationException {
        Credentials authCredentials =new Credentials();
        try {
            authCredentials = new ObjectMapper().readValue(
                    request.getReader(), Credentials.class
            );
        } catch (Exception e){
        }

        UsernamePasswordAuthenticationToken PAToken =
                new UsernamePasswordAuthenticationToken(
                        authCredentials.getCorreo(),
                        authCredentials.getContrasena(),
                        Collections.emptyList());
        return getAuthenticationManager().authenticate(PAToken);
    }


    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain,
            Authentication authentication
    ) throws IOException {
        UserDetailImp userDetails= (UserDetailImp) authentication.getPrincipal();
        String token = JWTokenUtils.createToken(userDetails);
        response.addHeader("Authorization", "Bearer "+ token);
        response.getWriter().flush();
    }

}
