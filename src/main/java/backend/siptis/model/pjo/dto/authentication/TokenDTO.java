package backend.siptis.model.pjo.dto.authentication;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TokenDTO {
    private String token;
    private String refreshToken;
}
