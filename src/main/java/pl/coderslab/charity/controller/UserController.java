package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.token.ConfirmationToken;
import pl.coderslab.charity.token.ConfirmationTokenService;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;
import pl.coderslab.charity.utils.PasswordUtils;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("")
public class UserController {
    private final UserService userService;
    private final PasswordUtils passwordUtils;
    private final ConfirmationTokenService confirmationTokenService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }

//REGISTER NEW ACCOUNT
    @GetMapping("/register")
    public String registerGet(Model model) {
        model.addAttribute("user", new User());
        return "admin/register";
    }

    @PostMapping("/register")
    public String registerPost(User user) {
        userService.save(user);
        return "admin/emailSent";
    }

//CONFIRMATION FROM EMAIL
    @GetMapping("/register/confirm")
    public String registerConfirmGet(@RequestParam String token){
        ConfirmationToken confirmationToken = confirmationTokenService.findByToken(token);
        User user = confirmationToken.getUser();
        user.setEnabled(true);
        userService.edit(user);
        confirmationTokenService.delete(confirmationToken);
        return "user/activation";
    }

    //PROFILE EDIT
    @GetMapping("/profile/{userId}/edit")
    public String userEditGet(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.findById(userId));
        return "profile/edit";
    }

    @PostMapping("/profile/{userId}/edit")
    public String userEditPost(@PathVariable Long userId, User user) {
        User userFromDB = userService.findById(userId);
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setEmail(user.getEmail());
        userService.edit(userFromDB);

        Set<GrantedAuthority> authorities = userFromDB
                .getRoles()
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getRoleType().toString()))
                .collect(Collectors.toSet());

        Authentication authentication = new UsernamePasswordAuthenticationToken(userFromDB.getEmail(), userFromDB.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/profile/" + userId + "/edit";
    }

    //PROFILE CHANGE PASSWORD
    @GetMapping("/profile/{userId}/change-pass")
    public String userChangePassGet(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.findById(userId));
        return "profile/changePasswd";
    }

    @PostMapping("/profile/{userId}/change-pass")
    public String userChangePassPost(
            @PathVariable Long userId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model
    ) {
        User user = userService.findById(userId);
        if (passwordUtils.checkOldPassword(user, oldPassword) && passwordUtils.isPasswordMatches(newPassword, confirmPassword)) {
            user.setPassword(newPassword);
            userService.saveUserPassword(user);
            return "redirect:/";
        } else {
            model.addAttribute("user", user);
            return "profile/changePasswdFail";
        }
    }

    //FORGOT PASSWORD
    @GetMapping("/register/forgot-pass")
    public String enterMailGet(){
        return "user/forgotPassSetEmail";
    }

    @PostMapping("/register/forgot-pass")
    public String enterMailPost(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if(user==null){
            return "user/emailNotFound";
        }
        userService.forgotPass(user);
        return "user/newPassMailSent";
    }

    //FORGOT SET NEW PASSWORD
    @GetMapping("/register/forgot-pass/set-new")
    public String setNewPassGet(@RequestParam String token, Model model){
        model.addAttribute("token", token);
        return "user/forgotPassSetNewPass";
    }

    @PostMapping("/register/forgot-pass/set-new")
    public String setNewPassPost(
            @RequestParam String token,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model
    ){
        ConfirmationToken confirmationToken;

        try {
            confirmationToken = confirmationTokenService.findByToken(token);
        }catch (IllegalStateException e){
            return "user/notValidToken";
        }

        if(newPassword.equals(confirmPassword)){
            User user = confirmationToken.getUser();
            user.setPassword(newPassword);
            userService.saveUserPassword(user);
            confirmationTokenService.delete(confirmationToken);
            return "user/passwordChanged";
        }
        else {
            model.addAttribute("token", token);
            return "user/forgotPassSetNewPassFail";
        }
    }
}
