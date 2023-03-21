package backend.siptis.commons;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespuestaController {
    @Builder.Default
    private Date timestamp = new Date();
    private String message;
    private Object data;
}
