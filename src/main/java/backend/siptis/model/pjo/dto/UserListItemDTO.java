package backend.siptis.model.pjo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


public interface UserListItemDTO {

    Long getId();
    String getEmail();
    String getNames();
    String getLastnames();
    String getCodSIS();
}
