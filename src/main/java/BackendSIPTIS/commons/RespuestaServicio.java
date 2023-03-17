package BackendSIPTIS.commons;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespuestaServicio {
    private MensajeServicio mensajeServicio;
    private Object data;
}
