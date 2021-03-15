package pl.coderslab.charity.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.role.Role;
import pl.coderslab.charity.role.RoleType;
import pl.coderslab.charity.role.RoleService;
import pl.coderslab.charity.security.email.EmailService;
import pl.coderslab.charity.token.ConfirmationToken;
import pl.coderslab.charity.token.ConfirmationTokenService;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final ConfirmationTokenService tokenService;
    private final EmailService emailService;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllByRoleType(RoleType roleType) {
        Role role = roleService.findByRoleType(roleType);
        return userRepository.findAllByRoleType(role);
    }

    @Override
    public void save(User user) {
        user.setEnabled(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleService.findByRoleType(RoleType.ROLE_USER)));

        ConfirmationToken token = new ConfirmationToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());

        userRepository.save(user);
        tokenService.save(token);
        //todo:send email
        emailService.send(
                user.getEmail(),
                "Charity.com: Aktywacja konta",
                buildEmail(
                        user.getFirstName(),
                        "http://localhost:8080/register/confirm?token="+token.getToken()
                )
        );

    }

    @Override
    public void saveUserPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(String.format("User with id=%s does not exists", id)));
    }

    private String buildEmail(String name, String link) {
        return String.format("Cześć %s, kliknij w ten link aby aktywować swoje konto %s", name, link);
    }
}
