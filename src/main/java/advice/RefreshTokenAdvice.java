package advice;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.exception.RefreshTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class RefreshTokenAdvice {
    @ExceptionHandler(value = RefreshTokenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<?> handleTokenRefreshException(RefreshTokenException ex, WebRequest request) {

        ControllerAnswer controllerAnswer =
                ControllerAnswer.builder().data(null)
                        .message(ex.getMessage()).build();
        return new ResponseEntity<>(controllerAnswer, HttpStatus.FORBIDDEN);

    }
}
