package backend.siptis.exception;

import io.jsonwebtoken.io.IOException;

public class UserNotFoundException extends IOException {

    public UserNotFoundException(String msg) {
        super(msg);
    }

}
