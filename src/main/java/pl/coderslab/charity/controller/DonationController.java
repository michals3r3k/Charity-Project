package pl.coderslab.charity.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.donation.Donation;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.UserService;

@Controller
@AllArgsConstructor
public class DonationController {
    private final DonationService donationService;
    private final CategoryService categoryService;
    private final InstitutionService institutionService;
    private final UserService userService;

    @GetMapping("/donation")
    public String donationFormGet(Model model, Authentication auth) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("institutions", institutionService.findAll());
        model.addAttribute("currentUser", userService.findByEmail(auth.getName()));
        return "donation/form";
    }

    @PostMapping("/donation")
    public String donationFormPost(Donation donation) {
        donationService.save(donation);
        return "redirect:/donation/confirm";
    }

    @GetMapping("/donation/confirm")
    public String confirmDonation() {
        return "donation/formConfirm";
    }
}
