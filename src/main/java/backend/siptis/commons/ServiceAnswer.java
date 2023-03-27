package backend.siptis.commons;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceAnswer {
    private ServiceMessage serviceMessage;
    private Object data;
}
