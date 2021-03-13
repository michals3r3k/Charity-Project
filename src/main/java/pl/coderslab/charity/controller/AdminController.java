package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final InstitutionService institutionService;

    public AdminController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("")
    public String adminPanel(Model model){
        model.addAttribute("institutions", institutionService.findAll());
        return "admin/panel";
    }

    @GetMapping("/institution/edit/{id}")
    public String institutionEditGet(@PathVariable Long id, Model model){
        Institution institution = institutionService.findById(id);
        model.addAttribute("institution", institution);
        return "institution/edit";
    }

    @PostMapping("/institution/edit")
    public String institutionEditPost(Institution institution){
        institutionService.save(institution);
        return "redirect:/admin";
    }

}
