package pl.coderslab.charity.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

@Component
@AllArgsConstructor
public class UserSecurity {
    private final UserService userService;

    public boolean isCurrentUser(Authentication auth, Long id) {
        User user = userService.findById(id);
        return auth.getName().equals(user.getEmail());
    }

    public boolean isEnabled(Authentication authentication) {
        return userService.findByEmail(authentication.getName()).isEnabled();
    }
}
