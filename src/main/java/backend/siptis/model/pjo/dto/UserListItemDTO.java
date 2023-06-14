package backend.siptis.model.pjo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserListItemDTO {
    private Long id;
    private String name;
    private String lastname;
    private String role;
}
