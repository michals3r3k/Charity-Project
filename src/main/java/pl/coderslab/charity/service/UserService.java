package pl.coderslab.charity.service;

import pl.coderslab.charity.model.User;

public interface UserService {
    User findByEmail(String email);
    void save(User user);
    void delete(User user);
    User findById(Long id);
}
