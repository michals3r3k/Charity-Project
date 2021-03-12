package pl.coderslab.charity.service;

import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.RoleType;

public interface RoleService {
    Role findByRoleType(RoleType roleType);
}
