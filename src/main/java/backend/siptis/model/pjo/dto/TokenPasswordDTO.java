package backend.siptis.model.pjo.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class TokenPasswordDTO {
    @NonNull
    private String password;
    @NonNull
    private String tokenPassword;
}
