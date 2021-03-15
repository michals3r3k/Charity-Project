package pl.coderslab.charity.user;

import pl.coderslab.charity.role.RoleType;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
    List<User> findAll();
    void saveUserPassword(User user);
    void save(User user);
    void delete(User user);
    void edit(User user);
    User findById(Long id);
    List<User> findAllByRoleType(RoleType roleType);
}
