package backend.siptis.model.pjo.dto;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
public class ResponseDTO {
    String message;
    String data;
}
