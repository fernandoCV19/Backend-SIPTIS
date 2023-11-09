package backend.siptis.auth.jwt;

import backend.siptis.exception.UserNotFoundException;
import backend.siptis.model.pjo.dto.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
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
            throws ServletException, IOException {

        String bearerToken =request.getHeader("Authorization");
        try{
            if(bearerToken != null && bearerToken.startsWith("Bearer ")){
                String token =bearerToken.replace("Bearer ", "");
                if(JWTokenUtils.validateJwtToken(token)){
                    UsernamePasswordAuthenticationToken
                            usernamePAT = JWTokenUtils.getAuthentication(token);
                    SecurityContextHolder.getContext().setAuthentication(usernamePAT);
                }else{
                }
            }
            filterChain.doFilter(request, response);
        }catch(ExpiredJwtException ex){
            ObjectMapper mapper = new ObjectMapper();
            ResponseDTO dto = new ResponseDTO();
            dto.setData("");
            dto.setMessage("EXPIRED_JWT");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write(mapper.writeValueAsString(dto));
        }catch(MalformedJwtException ex){
            ObjectMapper mapper = new ObjectMapper();
            ResponseDTO dto = new ResponseDTO();
            dto.setData("");
            dto.setMessage("MALFORMED_JWT");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write(mapper.writeValueAsString(dto));
        }catch(UnsupportedJwtException ex){
            ObjectMapper mapper = new ObjectMapper();
            ResponseDTO dto = new ResponseDTO();
            dto.setData("");
            dto.setMessage("UNSUPPORTED_JWT");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write(mapper.writeValueAsString(dto));
        }catch(IllegalArgumentException ex){
            ObjectMapper mapper = new ObjectMapper();
            ResponseDTO dto = new ResponseDTO();
            dto.setData("");
            dto.setMessage("ILLEGAL_ARGUMENT_JWT");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write(mapper.writeValueAsString(dto));
        }catch(SignatureException ex){
            ObjectMapper mapper = new ObjectMapper();
            ResponseDTO dto = new ResponseDTO();
            dto.setData("");
            dto.setMessage("SIGNATURE_EXCEPTION_JWT");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write(mapper.writeValueAsString(dto));
        }catch(UserNotFoundException ex){
            ObjectMapper mapper = new ObjectMapper();
            ResponseDTO dto = new ResponseDTO();
            dto.setData("");
            dto.setMessage("USER_NOT_FOUND");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(401);
            response.getWriter().write(mapper.writeValueAsString(dto));
        }

    }


}

