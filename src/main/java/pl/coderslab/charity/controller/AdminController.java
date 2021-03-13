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
import pl.coderslab.charity.role.Role;
import pl.coderslab.charity.role.RoleService;
import pl.coderslab.charity.role.RoleType;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(InstitutionService institutionService, DonationService donationService, UserService userService, RoleService roleService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String adminPanel(Model model){
        model.addAttribute("countBags", donationService.countSum());
        model.addAttribute("countDonations", donationService.countAll());

        model.addAttribute("institutions", institutionService.findAll());
        model.addAttribute("admins", userService.findAllByRoleType(RoleType.ROLE_ADMIN));
        model.addAttribute("users", userService.findAll());

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

//----------------------------------------------
//ADMINS TAKE OFF PERMISSIONS
    @GetMapping("/admin/take-off-permissions/{id}")
    public String takeOffPermissionsGet(@PathVariable Long id){
        User user = userService.findById(id);
        Role roleTypeAdmin = roleService.findByRoleType(RoleType.ROLE_ADMIN);
        Role roleTypeUser = roleService.findByRoleType(RoleType.ROLE_USER);

        Set<Role> roles = user.getRoles();
        roles.remove(roleTypeAdmin);
        roles.add(roleTypeUser);
        user.setRoles(roles);

        userService.edit(user);

        return "redirect:/admin#admins";
    }

//---------------------------------------------
//USER EDIT
    @GetMapping("/user/edit/{id}")
    public String userEditGet(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("enables", List.of(true, false));
        return "user/edit";
    }

    @PostMapping("/user/edit")
    public String userEditPost(User user){
        User userFromDB = userService.findById(user.getId());
        user.setPassword(userFromDB.getPassword());
        user.setEnabled(userFromDB.isEnabled());

        userService.edit(user);
        return "redirect:/admin#users";
    }

//USER LOCK/UNLOCK
    @GetMapping("/user/switch-enable/{id}")
    public String switchEnableGet(@PathVariable Long id){
        User user = userService.findById(id);
        user.setEnabled(!user.isEnabled());
        userService.edit(user);
        return "redirect:/admin#users";
    }

//USER DELETE
    @GetMapping("/user/delete/{id}")
    public String userDeleteGet(@PathVariable Long id){
        User user = userService.findById(id);
        userService.delete(user);
        return "redirect:/admin#users";
    }


}
