package backend.siptis.service.userData;

import backend.siptis.commons.ServiceAnswer;

public interface RoleService {

    ServiceAnswer getAllowedRoles();
    ServiceAnswer getRoleByName(String name);
}
