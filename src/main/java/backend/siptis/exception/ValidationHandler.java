package backend.siptis.exception;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceMessage;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<Object> handleExpiredJwtException(
            ExpiredJwtException ex, WebRequest request) {
        System.out.println(1232132);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "El token ya expiro, intente de nuevo");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            // String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        ControllerAnswer answer = ControllerAnswer.builder()
                .data(errors.get(0)).message(ServiceMessage.ERROR_VALIDATION.toString()).build();

        return new ResponseEntity<Object>(answer, HttpStatus.BAD_REQUEST);
    }
}
