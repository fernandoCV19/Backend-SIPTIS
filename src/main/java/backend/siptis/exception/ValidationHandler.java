package backend.siptis.exception;

import backend.siptis.commons.ControllerAnswer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ArrayList<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String message = error.getDefaultMessage();
            errors.add(message);
        });
        ControllerAnswer answer = ControllerAnswer.builder()
                .data(null).message(errors.get(0)).build();

        return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
    }
}
