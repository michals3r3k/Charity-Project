package pl.coderslab.charity.user;

import pl.coderslab.charity.role.RoleType;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
    List<User> findAll();
    void saveUserPassword(User user);
    boolean save(User user);
    void delete(User user);
    void edit(User user);
    boolean forgotPass(String email);
    User findById(Long id);
    List<User> findAllByRoleType(RoleType roleType);
    void confirmEmail(String token);
    int setNewPassword(User user, String oldPassword, String newPassword, String confirmPassword);
    boolean isUserExists(String email);
    void editUsersDetails(Long userId, User user);
    int remindPassword(String token, String newPassword, String confirmPassword);
    void takeOffAdminPermissions(User user);
}
