package pl.coderslab.charity.user;

import pl.coderslab.charity.user.User;

public interface UserService {
    User findByEmail(String email);
    void save(User user);
    void delete(User user);
    User findById(Long id);
}
