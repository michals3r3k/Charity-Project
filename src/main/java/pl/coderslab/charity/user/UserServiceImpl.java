package pl.coderslab.charity.user;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.email.MyMailMessage;
import pl.coderslab.charity.role.Role;
import pl.coderslab.charity.role.RoleType;
import pl.coderslab.charity.role.RoleService;
import pl.coderslab.charity.email.EmailService;
import pl.coderslab.charity.token.ConfirmationToken;
import pl.coderslab.charity.token.ConfirmationTokenService;
import pl.coderslab.charity.token.TokenType;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final static String LOCAL_VERIFY_LINK = "http://localhost:8080/register/confirm?token=";
    private final static String LOCAL_FORGOT_PASS_LINK = "http://localhost:8080/register/forgot-pass/set-new?token=";
    private final static String LOCAL_VERIFY_SUBJECT = "Charity.com: Aktywacja konta";
    private final static String LOCAL_FORGOT_PASS_SUBJECT = "Charity.com: Zapomniane hasło";
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
    @Transactional
    public void save(User user) {
        user.setEnabled(false);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Set.of(roleService.findByRoleType(RoleType.ROLE_USER)));

        ConfirmationToken token = new ConfirmationToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setTokenType(TokenType.ACTIVATION);
        token.setUsed(false);

        userRepository.save(user);
        tokenService.save(token);

        MyMailMessage message = MyMailMessage.builder()
                .toUser(user)
                .link(LOCAL_VERIFY_LINK + token.getToken())
                .subject(LOCAL_VERIFY_SUBJECT)
                .build();
        message.buildEmailVerify();

        emailService.send(message);
    }

    @Override
    @Transactional
    public boolean forgotPass(String email) {
        if (isUserExists(email)) {
            User user = findByEmail(email);
            ConfirmationToken token = new ConfirmationToken();
            token.setUsed(false);
            token.setTokenType(TokenType.PASSWORD);
            token.setToken(UUID.randomUUID().toString());
            token.setUser(user);

            tokenService.save(token);

            MyMailMessage message = MyMailMessage.builder()
                    .toUser(user)
                    .subject(LOCAL_FORGOT_PASS_SUBJECT)
                    .link(LOCAL_FORGOT_PASS_LINK + token.getToken())
                    .build();
            message.buildEmailForgotPass();

            emailService.send(message);
        }

        return isUserExists(email);
    }

    @Override
    @Transactional
    public void confirmEmail(String token) {
        ConfirmationToken confirmationToken = tokenService.findByToken(token);
        User user = confirmationToken.getUser();
        user.setEnabled(true);
        edit(user);
        tokenService.delete(confirmationToken);
    }

    @Override
    public boolean setNewPassword(User user, String oldPassword, String newPassword, String confirmPassword) {
        if (passwordEncoder.matches(oldPassword, user.getPassword()) && newPassword.equals(confirmPassword)) {
            user.setPassword(newPassword);
            saveUserPassword(user);
            return true;
        }
        return false;
    }

    @Override
    public void saveUserPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void editUsersDetails(Long userId, User user) {
        User userFromDB = findById(userId);
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setEmail(user.getEmail());
        edit(userFromDB);

        Set<GrantedAuthority> authorities = userFromDB
                .getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getRoleType().toString()))
                .collect(Collectors.toSet());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userFromDB.getEmail(), userFromDB.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
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
    public boolean isUserExists(String email) {
        return findByEmail(email) != null;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("User with id=%s does not exists", id)));
    }

    @Override
    @Transactional
    public int remindPassword(String token, String newPassword, String confirmPassword) {
        ConfirmationToken confirmationToken;
        try {
            confirmationToken = tokenService.findByToken(token);
        } catch (IllegalStateException e) {
            return 0;
        }

        if (newPassword.equals(confirmPassword)) {
            User user = confirmationToken.getUser();
            user.setPassword(newPassword);
            saveUserPassword(user);
            tokenService.delete(confirmationToken);
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void takeOffAdminPermissions(User user) {
        Role roleTypeAdmin = roleService.findByRoleType(RoleType.ROLE_ADMIN);
        Role roleTypeUser = roleService.findByRoleType(RoleType.ROLE_USER);

        Set<Role> roles = user.getRoles();
        roles.remove(roleTypeAdmin);
        roles.add(roleTypeUser);
        user.setRoles(roles);

        edit(user);
    }
}
