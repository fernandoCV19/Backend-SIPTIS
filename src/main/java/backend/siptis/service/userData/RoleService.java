package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;

import java.util.Set;

public interface RoleService {

    ServiceAnswer getAllowedRoles();

    ServiceAnswer getRoleByName(String name);

    Set<String> assignableRoles();

    Set<String> notAssignableRoles();

    Set<String> directorRoles();
}
