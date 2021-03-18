package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
    private static final String PASSWORD_NOT_VALID = "Hasło powinno mieć conajmniej 8 znaków i jedną: literę małą, leterę dużą, cyfrę i znak specjalny";
    private static final String BAD_OLD_PASSWORD = "Błędne stare hasło!";
    private final UserService userService;
    private final DonationService donationService;

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

        switch (userService.setNewPassword(user, oldPassword, newPassword, confirmPassword)){
            case 0:
                return "redirect:/";
            case 1:
                model.addAttribute("user", userService.findById(userId));
                model.addAttribute("message", PASSWORD_NOT_VALID);
                return "profile/changePasswdFail";
            case 2:
                model.addAttribute("user", userService.findById(userId));
                model.addAttribute("message", BAD_OLD_PASSWORD);
                return "profile/changePasswdFail";
        }
        return "profile/changePasswd";
    }

    //PROFILE USER DONATIONS
    @GetMapping("/{userId}/donations")
    public String userDonationsGet(@PathVariable Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("donations", donationService.findAllByUser(user));
        return "profile/donationList";
    }

    @GetMapping("/{userId}/donation/{donationId}")
    public String userDonationGet(@PathVariable Long userId, @PathVariable Long donationId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        model.addAttribute("donation", donationService.findById(donationId));
        return "profile/donationDetails";
    }

    @GetMapping("/{userId}/donations/mark-as-taken/{donationId}")
    public String userDonationsGet(@PathVariable Long userId,@PathVariable Long donationId) {
        donationService.switchTaken(donationId);
        return "redirect:/profile/"+userId+"/donations";
    }

}
