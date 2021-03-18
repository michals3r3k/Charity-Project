package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("")
public class UserController {
    private static final String PASSWORD_NOT_VALID = "Hasło powinno mieć conajmniej 8 znaków i jedną: literę małą, leterę dużą, cyfrę i znak specjalny";
    private static final String PASSWORDS_DIDNT_MATCH = "Hasła nie pasują do siebie!";
    private final UserService userService;

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
    public String registerPost(User user, Model model) {
        boolean save = userService.save(user);
        if(save){
            return "admin/emailSent";
        }
        model.addAttribute("message", PASSWORD_NOT_VALID);
        model.addAttribute("user", new User());
        return "admin/registerFail";
    }

    //CONFIRMATION FROM EMAIL
    @GetMapping("/register/confirm")
    public String registerConfirmGet(@RequestParam String token) {
        userService.confirmEmail(token);
        return "user/activation";
    }

    //FORGOT PASSWORD
    @GetMapping("/register/forgot-pass")
    public String enterMailGet() {
        return "user/forgotPassSetEmail";
    }

    @PostMapping("/register/forgot-pass")
    public String enterMailPost(@RequestParam String email) {
        return userService.forgotPass(email) ? "user/newPassMailSent" : "user/emailNotFound";
    }

    //FORGOT SET NEW PASSWORD
    @GetMapping("/register/forgot-pass/set-new")
    public String setNewPassGet(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "user/forgotPassSetNewPass";
    }

    @PostMapping("/register/forgot-pass/set-new")
    public String setNewPassPost(
            @RequestParam String token,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model
    ) {
        switch (userService.remindPassword(token, newPassword, confirmPassword)){
            case 0:
                return "user/notValidToken";
            case 1:
                return "user/passwordChanged";
            case 2:
                model.addAttribute("token", token);
                model.addAttribute("message", PASSWORDS_DIDNT_MATCH);
                return "user/forgotPassSetNewPassFail";
            case 3:
                model.addAttribute("token", token);
                model.addAttribute("message", PASSWORD_NOT_VALID);
                return "user/forgotPassSetNewPassFail";
        }
        return "error/403";
    }
}
