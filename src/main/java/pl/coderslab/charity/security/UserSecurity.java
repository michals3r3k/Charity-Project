package pl.coderslab.charity.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

@Component
public class UserSecurity {
    private final UserService userService;

    public UserSecurity(UserService userService) {
        this.userService = userService;
    }

    public boolean isCurrentUser(Authentication auth, Long id){
        User user = userService.findById(id);
        return auth.getName().equals(user.getEmail());
    }
}
