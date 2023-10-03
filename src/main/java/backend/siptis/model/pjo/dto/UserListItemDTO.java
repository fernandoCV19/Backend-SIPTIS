package backend.siptis.model.pjo.dto;

public interface UserListItemDTO {
    //private Long id;
    //private String name;
    //private String lastname;
    //private String role;
    Long getId();

    String getEmail();

    String getNames();

    String getLastnames();

    String getCodSIS();
}
