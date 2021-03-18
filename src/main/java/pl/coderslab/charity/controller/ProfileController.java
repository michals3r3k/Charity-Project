package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    //PROFILE EDIT
    @GetMapping("/{userId}/edit")
    public String userEditGet(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.findById(userId));
        return "profile/edit";
    }

    @PostMapping("/{userId}/edit")
    public String userEditPost(@PathVariable Long userId, User user) {
        userService.editUsersDetails(userId, user);

        return "redirect:/profile/" + userId + "/edit";
    }

    //PROFILE CHANGE PASSWORD
    @GetMapping("/{userId}/change-pass")
    public String userChangePassGet(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.findById(userId));
        return "profile/changePasswd";
    }

    @PostMapping("/{userId}/change-pass")
    public String userChangePassPost(
            @PathVariable Long userId,
            @RequestParam String oldPassword,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            Model model
    ) {
        User user = userService.findById(userId);

        if(userService.setNewPassword(user, oldPassword, newPassword, confirmPassword)){
            return "redirect:/";
        }

        model.addAttribute("user", user);
        return "profile/changePasswdFail";
    }
}
