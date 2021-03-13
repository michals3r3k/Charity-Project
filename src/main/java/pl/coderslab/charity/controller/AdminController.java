package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final InstitutionService institutionService;
    private final DonationService donationService;

    public AdminController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping("")
    public String adminPanel(Model model){
        model.addAttribute("countBags", donationService.countSum());
        model.addAttribute("countDonations", donationService.countAll());

        model.addAttribute("institutions", institutionService.findAll());

        return "admin/panel";
    }

//INSTITUTION EDIT
    @GetMapping("/institution/edit/{id}")
    public String institutionEditGet(@PathVariable Long id, Model model){
        Institution institution = institutionService.findById(id);
        model.addAttribute("institution", institution);
        return "institution/edit";
    }

    @PostMapping("/institution/edit")
    public String institutionEditPost(Institution institution){
        institutionService.save(institution);
        return "redirect:/admin#institutions";
    }

//INSTITUTION ADD
    @GetMapping("/institution/add")
    public String institutionAddGet(Model model){
        model.addAttribute("institution", new Institution());
        return "institution/add";
    }

    @PostMapping("/institution/add")
    public String institutionAddPost(Institution institution){
        institutionService.save(institution);
        return "redirect:/admin#institutions";
    }

//INSTITUTION DELETE
    @GetMapping("/institution/delete/{id}")
    public String institutionDeleteGet(@PathVariable Long id){
        Institution institution = institutionService.findById(id);
        institutionService.delete(institution);
        return "redirect:/admin#institutions";
    }

}
