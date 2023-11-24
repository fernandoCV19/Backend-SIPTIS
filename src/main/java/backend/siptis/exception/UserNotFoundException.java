package backend.siptis.exception;

import io.jsonwebtoken.JwtException;

public class UserNotFoundException extends JwtException {

    public UserNotFoundException(String msg) {
        super(msg);
    }

}
