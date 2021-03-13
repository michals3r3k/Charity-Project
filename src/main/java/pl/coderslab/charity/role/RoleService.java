package pl.coderslab.charity.role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role findByRoleType(RoleType roleType);
    List<Role> findAll();
}
