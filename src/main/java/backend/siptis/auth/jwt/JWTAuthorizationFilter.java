package backend.siptis.auth.jwt;

import backend.siptis.exception.RefreshTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException, RefreshTokenException {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.replace("Bearer ", "");
            if (JWTokenUtils.validateJwtToken(token)) {
                UsernamePasswordAuthenticationToken
                        usernamePAT = JWTokenUtils.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(usernamePAT);
            }

        }

        filterChain.doFilter(request, response);
    }


}

