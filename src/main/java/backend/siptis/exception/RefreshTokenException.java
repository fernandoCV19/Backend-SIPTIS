package backend.siptis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RefreshTokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RefreshTokenException(String message) {
        super("Ocurrio un error con el refresh token: " + message);
    }
}
