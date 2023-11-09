package backend.siptis.auth.security;

import backend.siptis.model.pjo.dto.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        ResponseDTO dto = new ResponseDTO();
        dto.setData("Access Denied.");
        dto.setMessage("FORBIDDEN");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(403);
        response.getWriter().write(mapper.writeValueAsString(dto));
    }
}
