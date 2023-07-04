package backend.siptis.service.userData.checkUserInformation;

public interface CheckUserInformation {

    boolean existByCodSIS(String codSIS);

    boolean existByCi(String ci);

    boolean existsById(Long id);

    boolean existsByEmail(String email);

    boolean existsTokenPassword(String tokenPassword);
}
