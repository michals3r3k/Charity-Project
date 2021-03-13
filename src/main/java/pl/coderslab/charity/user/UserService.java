package pl.coderslab.charity.user;

import pl.coderslab.charity.role.Role;
import pl.coderslab.charity.role.RoleType;
import pl.coderslab.charity.user.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
    void save(User user);
    void delete(User user);
    void edit(User user);
    User findById(Long id);
    List<User> findAllByRoleType(RoleType roleType);
}
