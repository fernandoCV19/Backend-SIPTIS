package backend.siptis.exception;

import backend.siptis.commons.ControllerAnswer;
import backend.siptis.commons.ServiceAnswer;
import backend.siptis.commons.ServiceMessage;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class JwtExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ControllerAnswer expiredTokenException(ExpiredJwtException exception, WebRequest webRequest){
        ControllerAnswer controllerAnswer = ControllerAnswer.builder()
                .data("El token expiro.").message(ServiceMessage.ERROR.toString()).build();
        return controllerAnswer;
    }

}
