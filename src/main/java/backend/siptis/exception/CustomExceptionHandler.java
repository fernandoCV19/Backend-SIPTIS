package backend.siptis.exception;

import backend.siptis.model.pjo.dto.user_data.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseDTO> handleAccessDeniedException(AccessDeniedException ex) {
        ResponseDTO dto = new ResponseDTO();
        dto.setMessage("UNAUTHORIZED");
        return new ResponseEntity<>(dto, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomAccessDeniedException.class)
    public ResponseEntity<String> handleCustomAccessDeniedException(CustomAccessDeniedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
}
